package com.example.one_mobile.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.one_mobile.data.local.RiskEvaluation;
import com.example.one_mobile.data.repository.RiskRepository;

import java.util.List;

public class RiskViewModel extends AndroidViewModel {
    private final RiskRepository repository;
    private final LiveData<List<RiskEvaluation>> allRisks;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public RiskViewModel(Application application) {
        super(application);
        repository = new RiskRepository(application);
        allRisks = repository.getRisks();
    }

    /**
     * Retourne tous les risques sous forme observable.
     */
    public LiveData<List<RiskEvaluation>> getAllRisks() {
        return allRisks;
    }

    /**
     * Indique si les données sont en cours de chargement.
     */
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * Ajoute un nouveau risque.
     */
    public void addRisk(RiskEvaluation risk) {
        isLoading.setValue(true);
        new Thread(() -> {
            repository.addRisk(risk);
            isLoading.postValue(false);
        }).start();
    }

    /**
     * Met à jour un risque existant.
     */
    public void updateRisk(RiskEvaluation risk) {
        isLoading.setValue(true);
        new Thread(() -> {
            repository.updateRisk(risk);
            isLoading.postValue(false);
        }).start();
    }

    /**
     * Supprime un risque existant.
     */
    public void deleteRisk(RiskEvaluation risk) {
        isLoading.setValue(true);
        new Thread(() -> {
            repository.deleteRisk(risk);
            isLoading.postValue(false);
        }).start();
    }

    /**
     * Lance la synchronisation avec le serveur.
     */
    public void synchronize(String lastSyncTimestamp) {
        isLoading.setValue(true);
        new Thread(() -> {
            repository.synchronize(lastSyncTimestamp);
            isLoading.postValue(false);
        }).start();
    }
}
