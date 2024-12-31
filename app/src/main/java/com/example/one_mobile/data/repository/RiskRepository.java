package com.example.one_mobile.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.one_mobile.data.local.AppDatabase;
import com.example.one_mobile.data.model.RiskEvaluation;
import com.example.one_mobile.data.local.RiskEvaluationDao;
import com.example.one_mobile.data.model.SyncQueue;
import com.example.one_mobile.data.local.SyncQueueDao;
import com.example.one_mobile.data.network.ApiService;
import com.example.one_mobile.data.network.RetrofitClient;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiskRepository {
    private final RiskEvaluationDao riskDao;
    private final SyncQueueDao syncQueueDao;
    private final ApiService apiService;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Gson gson = new Gson();

    public RiskRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        riskDao = db.riskEvaluationDao();
        syncQueueDao = db.syncQueueDao();
        apiService = RetrofitClient.getApiService();
    }

    /**
     * Retourne toutes les données locales sous forme observable.
     */
    public LiveData<List<RiskEvaluation>> getRisks() {
        return riskDao.getAllRisks();
    }

    /**
     * Ajoute un nouveau risque localement et enregistre l'opération dans la file de synchronisation.
     */
    public void addRisk(RiskEvaluation risk) {
        executor.execute(() -> {
            riskDao.insert(risk);
            String payload = gson.toJson(risk);
            SyncQueue syncAction = new SyncQueue("CREATE", "risk_evaluations", payload);
            syncQueueDao.insert(syncAction);
        });
    }

    /**
     * Met à jour un risque localement et enregistre l'opération dans la file de synchronisation.
     */
    public void updateRisk(RiskEvaluation risk) {
        executor.execute(() -> {
            riskDao.update(risk);
            String payload = gson.toJson(risk);
            SyncQueue syncAction = new SyncQueue("UPDATE", "risk_evaluations", payload);
            syncQueueDao.insert(syncAction);
        });
    }

    /**
     * Supprime un risque localement et enregistre l'opération dans la file de synchronisation.
     */
    public void deleteRisk(RiskEvaluation risk) {
        executor.execute(() -> {
            riskDao.delete(risk);
            // Enregistre dans la file de synchronisation pour le supprimer du serveur plus tard
            String payload = gson.toJson(risk);
            SyncQueue syncAction = new SyncQueue("DELETE", "risk_evaluations", payload);
            syncQueueDao.insert(syncAction);
        });
    }


    /**
     * Synchronise les données locales avec le serveur.
     * 1. Envoie les modifications locales au serveur.
     * 2. Récupère les données modifiées depuis le serveur et met à jour la base locale.
     */
    public void synchronize(String lastSyncTimestamp) {
        executor.execute(() -> {
            // 1. Envoyer les modifications locales
            List<SyncQueue> pendingSyncs = syncQueueDao.getAllPendingSync();
            for (SyncQueue sync : pendingSyncs) {
                handleSyncAction(sync);
            }

            // 2. Récupérer les nouvelles données du serveur
            apiService.getModifiedRisks(lastSyncTimestamp).enqueue(new Callback<List<RiskEvaluation>>() {
                @Override
                public void onResponse(Call<List<RiskEvaluation>> call, Response<List<RiskEvaluation>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        executor.execute(() -> {
                            for (RiskEvaluation risk : response.body()) {
                                if (riskDao.exists(risk.getId())) {
                                    riskDao.update(risk);  // Mise à jour si le risque existe
                                } else {
                                    riskDao.insert(risk);  // Insertion si le risque est nouveau
                                }
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<RiskEvaluation>> call, Throwable t) {
                    // Gérer les erreurs de réseau ou API
                    t.printStackTrace();
                }
            });
        });
    }

    /**
     * Gère une opération spécifique de la file de synchronisation (CREATE, UPDATE, DELETE).
     */
    private void handleSyncAction(SyncQueue sync) {
        String payload = sync.getPayload();
        RiskEvaluation risk = gson.fromJson(payload, RiskEvaluation.class);

        switch (sync.getAction()) {
            case "CREATE":
                apiService.addRisk(risk).enqueue(new Callback<RiskEvaluation>() {
                    @Override
                    public void onResponse(Call<RiskEvaluation> call, Response<RiskEvaluation> response) {
                        if (response.isSuccessful()) {
                            executor.execute(() -> syncQueueDao.deleteById(sync.getId()));
                        }
                    }

                    @Override
                    public void onFailure(Call<RiskEvaluation> call, Throwable t) {
                        // Gérer l'échec d'une synchronisation
                        t.printStackTrace();
                    }
                });
                break;

            case "UPDATE":
                apiService.updateRisk(risk).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            executor.execute(() -> syncQueueDao.deleteById(sync.getId()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Gérer l'échec d'une synchronisation
                        t.printStackTrace();
                    }
                });
                break;

            case "DELETE":
                // Implémentez une méthode DELETE dans l'API si nécessaire
                break;
        }
    }
}