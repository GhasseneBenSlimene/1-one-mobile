package com.example.one_mobile.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.network.ApiService;
import com.example.one_mobile.data.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluationSiteRepository {

    private final ApiService apiService;

    public EvaluationSiteRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<Site>> getAllSites() {
        MutableLiveData<List<Site>> sites = new MutableLiveData<>();
        apiService.getAllSites().enqueue(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sites.setValue(response.body());
                } else {
                    sites.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                sites.setValue(null);
            }
        });
        return sites;
    }

    public LiveData<List<Matrice>> getAllMatrices() {
        MutableLiveData<List<Matrice>> matrices = new MutableLiveData<>();
        apiService.getAllMatrices().enqueue(new Callback<List<Matrice>>() {
            @Override
            public void onResponse(Call<List<Matrice>> call, Response<List<Matrice>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    matrices.setValue(response.body());
                } else {
                    matrices.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Matrice>> call, Throwable t) {
                matrices.setValue(null);
            }
        });
        return matrices;
    }

    public LiveData<List<MatriceFacteur>> getMatriceFacteursByMatriceId(long matriceId) {
        MutableLiveData<List<MatriceFacteur>> matriceFacteurs = new MutableLiveData<>();
        apiService.getMatriceFacteursByMatriceId(matriceId).enqueue(new Callback<List<MatriceFacteur>>() {
            @Override
            public void onResponse(Call<List<MatriceFacteur>> call, Response<List<MatriceFacteur>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    matriceFacteurs.setValue(response.body());
                } else {
                    matriceFacteurs.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<MatriceFacteur>> call, Throwable t) {
                matriceFacteurs.setValue(null);
            }
        });
        return matriceFacteurs;
    }

    public LiveData<List<Valeur>> getValeursByFacteurId(long facteurId) {
        MutableLiveData<List<Valeur>> valeurs = new MutableLiveData<>();
        apiService.getValeursByFacteurId(facteurId).enqueue(new Callback<List<Valeur>>() {
            @Override
            public void onResponse(Call<List<Valeur>> call, Response<List<Valeur>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    valeurs.setValue(response.body());
                } else {
                    valeurs.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Valeur>> call, Throwable t) {
                valeurs.setValue(null);
            }
        });
        return valeurs;
    }

    public LiveData<Facteur> getFacteurById(long facteurId) {
        MutableLiveData<Facteur> facteur = new MutableLiveData<>();
        apiService.getFacteurById(facteurId).enqueue(new Callback<Facteur>() {
            @Override
            public void onResponse(Call<Facteur> call, Response<Facteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    facteur.setValue(response.body());
                } else {
                    facteur.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Facteur> call, Throwable t) {
                facteur.setValue(null);
            }
        });
        return facteur;
    }

    public LiveData<EvaluationSite> createEvaluationSite(EvaluationSite evaluationSite) {
        MutableLiveData<EvaluationSite> createdEvaluationSite = new MutableLiveData<>();
        apiService.createEvaluationSite(evaluationSite).enqueue(new Callback<EvaluationSite>() {
            @Override
            public void onResponse(Call<EvaluationSite> call, Response<EvaluationSite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    createdEvaluationSite.setValue(response.body());
                } else {
                    createdEvaluationSite.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<EvaluationSite> call, Throwable t) {
                createdEvaluationSite.setValue(null);
            }
        });
        return createdEvaluationSite;
    }
}
