package com.example.one_mobile.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.one_mobile.data.local.AppDatabase;
import com.example.one_mobile.data.local.Dao.EvaluationSiteDao;
import com.example.one_mobile.data.local.Dao.MatriceDao;
import com.example.one_mobile.data.local.Dao.OrigineDao;
import com.example.one_mobile.data.local.Dao.SiteDao;
import com.example.one_mobile.data.local.Dto.EvaluationSiteWithDetailsDTO;
import com.example.one_mobile.data.model.Evaluation;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.network.ApiService;
import com.example.one_mobile.data.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluationSiteRepository {


    private final ApiService apiService;
    private final EvaluationSiteDao evaluationSiteDao;
    private final MatriceDao matriceDao;
    private final OrigineDao origineDao;
    private final SiteDao siteDao;
    private final ExecutorService executor;
    private final Context context;
    private final AppDatabase database;

    public EvaluationSiteRepository(Context context) {
        apiService = RetrofitClient.getApiService();
        evaluationSiteDao = AppDatabase.getInstance(context).evaluationSiteDao();
        matriceDao = AppDatabase.getInstance(context).matriceDao();
        origineDao = AppDatabase.getInstance(context).origineDao();
        siteDao = AppDatabase.getInstance(context).siteDao();
        executor = Executors.newSingleThreadExecutor();
        this.database = AppDatabase.getInstance(context);
        this.context = context;
    }

    //
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

    public LiveData<List<Origine>> getAllOrigines() {
        MutableLiveData<List<Origine>> origines = new MutableLiveData<>();
        apiService.getAllOrigines().enqueue(new Callback<List<Origine>>() {
            @Override
            public void onResponse(Call<List<Origine>> call, Response<List<Origine>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    origines.setValue(response.body());
                } else {
                    origines.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Origine>> call, Throwable t) {
                origines.setValue(null);
            }
        });
        return origines;
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

    public void updateMatricesAndOrigines() {
        if (isNetworkAvailable()) {
            // Fetch matrices from the server
            apiService.getAllMatrices().enqueue(new Callback<List<Matrice>>() {
                @Override
                public void onResponse(Call<List<Matrice>> call, Response<List<Matrice>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Matrice> matrices = response.body();
                        executor.execute(() -> {
                            matriceDao.clearAll(); // Clear the local Matrice table
                            matriceDao.insertAll(matrices); // Insert the latest Matrices
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<Matrice>> call, Throwable t) {
                    // Handle failure (optional logging)
                }
            });

            // Fetch origines from the server
            apiService.getAllOrigines().enqueue(new Callback<List<Origine>>() {
                @Override
                public void onResponse(Call<List<Origine>> call, Response<List<Origine>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Origine> origines = response.body();
                        executor.execute(() -> {
                            origineDao.clearAll(); // Clear the local Origine table
                            origineDao.insertAll(origines); // Insert the latest Origines
                        });
                    }
                }

                @Override
                public void onFailure(Call<List<Origine>> call, Throwable t) {
                    // Handle failure (optional logging)
                }
            });
        } else {
            // Handle case when network is unavailable
            Log.e("Repository", "Network unavailable. Cannot update Matrices and Origines.");
        }
    }

    public LiveData<List<EvaluationSiteWithDetails>> getAllEvaluationSites() {
        MutableLiveData<List<EvaluationSiteWithDetails>> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getAllEvaluationSites().enqueue(new Callback<List<EvaluationSiteWithDetailsDTO>>() {
                @Override
                public void onResponse(Call<List<EvaluationSiteWithDetailsDTO>> call, Response<List<EvaluationSiteWithDetailsDTO>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<EvaluationSiteWithDetailsDTO> dtoList = response.body();
                        List<EvaluationSite> evaluationSites = new ArrayList<>();
                        List<Matrice> matrices = new ArrayList<>();
                        List<Origine> origines = new ArrayList<>();
                        List<Site> sites = new ArrayList<>();
                        List<Evaluation> evaluations = new ArrayList<>();

                        for (EvaluationSiteWithDetailsDTO dto : dtoList) {
                            // Map DTO to model and add to respective lists
                            evaluationSites.add(dto.toEvaluationSite());
                            evaluations.add(dto.getEvaluation().toEvaluation());
                            matrices.add(dto.getEvaluation().getMatrice());
                            if (dto.getEvaluation().getOrigine() != null) {
                                origines.add(dto.getEvaluation().getOrigine());
                            }
                            sites.add(dto.getSite());
                        }

                        // Save data to the local database
                        executor.execute(() -> {
                            database.matriceDao().insertAll(matrices);
                            database.origineDao().insertAll(origines);
                            database.siteDao().insertAll(sites);
                            database.evaluationDao().insertAll(evaluations);
                            database.evaluationSiteDao().insertAll(evaluationSites);

                            // Load updated data from local database
                            List<EvaluationSiteWithDetails> details = database.evaluationSiteDao().getAllEvaluationSitesWithDetailsSync();
                            liveData.postValue(details);
                        });
                    } else {
                        // Fallback to local data if API call fails
                        loadLocalData(liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<EvaluationSiteWithDetailsDTO>> call, Throwable t) {
                    // Fallback to local data on failure
                    loadLocalData(liveData);
                }
            });
        } else {
            // Load from local database if no network is available
            loadLocalData(liveData);
        }

        return liveData;
    }


    private void loadLocalData(MutableLiveData<List<EvaluationSiteWithDetails>> liveData) {
        executor.execute(() -> {
            List<EvaluationSiteWithDetails> localData = database.evaluationSiteDao().getAllEvaluationSitesWithDetailsSync();
            liveData.postValue(localData);
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


