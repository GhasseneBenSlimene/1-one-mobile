package com.example.one_mobile.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.one_mobile.data.local.AppDatabase;
import com.example.one_mobile.data.local.Dao.EvaluationSiteDao;
import com.example.one_mobile.data.local.Dao.FacteurDao;
import com.example.one_mobile.data.local.Dao.MatriceDao;
import com.example.one_mobile.data.local.Dao.OrigineDao;
import com.example.one_mobile.data.local.Dao.SiteDao;
import com.example.one_mobile.data.local.Dto.EvaluationSiteWithDetailsDTO;
import com.example.one_mobile.data.local.Dto.MatriceFacteurDto;
import com.example.one_mobile.data.model.Evaluation;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Risque;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.network.ApiService;
import com.example.one_mobile.data.network.RetrofitClient;
import com.example.one_mobile.data.network.TokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaluationSiteRepository {


    private final ApiService apiService;
    private final TokenRefresherRepository tokenRefresherRepository;
    private final EvaluationSiteDao evaluationSiteDao;
    private final MatriceDao matriceDao;
    private final OrigineDao origineDao;
    private final SiteDao siteDao;
    private final FacteurDao facteurDao;
    private final ExecutorService executor;
    private final Context context;
    private final AppDatabase database;

    public EvaluationSiteRepository(Context context) {
        apiService = RetrofitClient.getApiService();
        evaluationSiteDao = AppDatabase.getInstance(context).evaluationSiteDao();
        matriceDao = AppDatabase.getInstance(context).matriceDao();
        origineDao = AppDatabase.getInstance(context).origineDao();
        siteDao = AppDatabase.getInstance(context).siteDao();
        facteurDao = AppDatabase.getInstance(context).facteurDao();
        executor = Executors.newSingleThreadExecutor();
        this.database = AppDatabase.getInstance(context);
        this.context = context;
        tokenRefresherRepository = new TokenRefresherRepository();
    }

    //
    public LiveData<List<Site>> getAllSites() {
        MutableLiveData<List<Site>> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getAllSites().enqueue(new Callback<List<Site>>() {
                @Override
                public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Site> sites = response.body();

                        executor.execute(() -> {
                            try {
                                database.siteDao().insertAll(sites); // Insert or update
                                Log.d("Repository", "Sites inserted/updated successfully.");
                            } catch (Exception e) {
                                Log.e("Repository", "Error inserting/updating Sites", e);
                            }

                            // Load data from the database after insert/update
                            List<Site> localSites = database.siteDao().getAllSitesSync();
                            liveData.postValue(localSites);
                        });
                    } else {
                        Log.e("Repository", "API response for Sites unsuccessful.");
                        loadSitesFromLocalDatabase(liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<Site>> call, Throwable t) {
                    Log.e("Repository", "Failed to fetch Sites from API", t);
                    loadSitesFromLocalDatabase(liveData);
                }
            });
        } else {
            Log.d("Repository", "No network available, loading Sites from local database.");
            loadSitesFromLocalDatabase(liveData);
        }

        return liveData;
    }

    private void loadSitesFromLocalDatabase(MutableLiveData<List<Site>> liveData) {
        executor.execute(() -> {
            List<Site> localSites = database.siteDao().getAllSitesSync();
            liveData.postValue(localSites);
        });
    }

    public LiveData<List<Risque>> getAllRisques() {
        MutableLiveData<List<Risque>> risques = new MutableLiveData<>();
        apiService.getAllRisques().enqueue(new Callback<List<Risque>>() {
            @Override
            public void onResponse(Call<List<Risque>> call, Response<List<Risque>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    risques.setValue(response.body());
                } else {
                    risques.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Risque>> call, Throwable t) {
                risques.setValue(null);
            }
        });
        return risques;
    }

    public LiveData<List<Origine>> getAllOrigines() {
        MutableLiveData<List<Origine>> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getAllOrigines().enqueue(new Callback<List<Origine>>() {
                @Override
                public void onResponse(Call<List<Origine>> call, Response<List<Origine>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Origine> origines = response.body();

                        executor.execute(() -> {
                            try {
                                database.origineDao().insertAll(origines); // Insert or update
                                Log.d("Repository", "Origines inserted/updated successfully.");
                            } catch (Exception e) {
                                Log.e("Repository", "Error inserting/updating Origines", e);
                            }

                            // Load data from the database after insert/update
                            List<Origine> localOrigines = database.origineDao().getAllOriginesSync();
                            liveData.postValue(localOrigines);
                        });
                    } else {
                        Log.e("Repository", "API response for Origines unsuccessful.");
                        loadOriginesFromLocalDatabase(liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<Origine>> call, Throwable t) {
                    Log.e("Repository", "Failed to fetch Origines from API", t);
                    loadOriginesFromLocalDatabase(liveData);
                }
            });
        } else {
            Log.d("Repository", "No network available, loading Origines from local database.");
            loadOriginesFromLocalDatabase(liveData);
        }

        return liveData;
    }

    private void loadOriginesFromLocalDatabase(MutableLiveData<List<Origine>> liveData) {
        executor.execute(() -> {
            List<Origine> localOrigines = database.origineDao().getAllOriginesSync();
            liveData.postValue(localOrigines);
        });
    }

    public LiveData<List<Matrice>> getAllMatrices() {
        MutableLiveData<List<Matrice>> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getAllMatrices().enqueue(new Callback<List<Matrice>>() {
                @Override
                public void onResponse(Call<List<Matrice>> call, Response<List<Matrice>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Matrice> matrices = response.body();

                        executor.execute(() -> {
                            try {
                                database.matriceDao().insertAll(matrices); // Insert or update
                                Log.d("Repository", "Matrices inserted/updated successfully.");
                            } catch (Exception e) {
                                Log.e("Repository", "Error inserting/updating Matrices", e);
                            }

                            // Load data from the database after insert/update
                            List<Matrice> localMatrices = database.matriceDao().getAllMatricesSync();
                            liveData.postValue(localMatrices);
                        });
                    } else {
                        Log.e("Repository", "API response for Matrices unsuccessful.");
                        loadMatricesFromLocalDatabase(liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<Matrice>> call, Throwable t) {
                    Log.e("Repository", "Failed to fetch Matrices from API", t);
                    loadMatricesFromLocalDatabase(liveData);
                }
            });
        } else {
            Log.d("Repository", "No network available, loading Matrices from local database.");
            loadMatricesFromLocalDatabase(liveData);
        }

        return liveData;
    }

    private void loadMatricesFromLocalDatabase(MutableLiveData<List<Matrice>> liveData) {
        executor.execute(() -> {
            List<Matrice> localMatrices = database.matriceDao().getAllMatricesSync();
            liveData.postValue(localMatrices);
        });
    }

    public LiveData<List<Facteur>> getFacteursByMatriceId(long matriceId) {
        MutableLiveData<List<Facteur>> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getMatriceFacteursByMatriceId(matriceId).enqueue(new Callback<List<MatriceFacteurDto>>() {
                @Override
                public void onResponse(Call<List<MatriceFacteurDto>> call, Response<List<MatriceFacteurDto>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<MatriceFacteurDto> dtoList = response.body();
                        List<Facteur> facteurs = new ArrayList<>();
                        List<MatriceFacteur> matriceFacteurs = new ArrayList<>();

                        for (MatriceFacteurDto dto : dtoList) {
                            Facteur facteur = dto.getFacteur(); // Récupérer le Facteur complet depuis le DTO
                            if (facteur != null) {
                                facteurs.add(facteur);

                                // Ajouter la relation dans MatriceFacteur
                                MatriceFacteur matriceFacteur = new MatriceFacteur();
                                matriceFacteur.setMatriceId(matriceId);
                                matriceFacteur.setFacteurId(facteur.getId());
                                matriceFacteurs.add(matriceFacteur);
                            }
                        }

                        executor.execute(() -> {
                            try {
                                // Enregistrer les Facteurs et les relations MatriceFacteur
                                database.facteurDao().insertAll(facteurs);
                                database.matriceFacteurDao().insertAll(matriceFacteurs);
                                Log.d("Repository", "Facteurs and MatriceFacteurs successfully inserted/updated.");

                                // Charger les Facteurs locaux associés à la Matrice
                                List<Facteur> localFacteurs = database.matriceFacteurDao().getFacteursByMatriceId(matriceId);
                                liveData.postValue(localFacteurs);
                            } catch (Exception e) {
                                Log.e("Repository", "Error inserting Facteurs or MatriceFacteurs", e);
                            }
                        });
                    } else {
                        Log.e("Repository", "API response unsuccessful for MatriceFacteurs.");
                        loadFacteursFromLocalDatabase(matriceId, liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<MatriceFacteurDto>> call, Throwable t) {
                    Log.e("Repository", "Failed to fetch MatriceFacteurs from API", t);
                    loadFacteursFromLocalDatabase(matriceId, liveData);
                }
            });
        } else {
            Log.d("Repository", "No network available, loading Facteurs from local database.");
            loadFacteursFromLocalDatabase(matriceId, liveData);
        }

        return liveData;
    }

    private void loadFacteursFromLocalDatabase(long matriceId, MutableLiveData<List<Facteur>> liveData) {
        executor.execute(() -> {
            try {
                List<Facteur> localFacteurs = database.matriceFacteurDao().getFacteursByMatriceId(matriceId);
                liveData.postValue(localFacteurs);
            } catch (Exception e) {
                Log.e("Repository", "Error loading Facteurs from local database", e);
            }
        });
    }

    public LiveData<List<Valeur>> getValeursByFacteurId(long facteurId) {
        MutableLiveData<List<Valeur>> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getValeursByFacteurId(facteurId).enqueue(new Callback<List<Valeur>>() {
                @Override
                public void onResponse(Call<List<Valeur>> call, Response<List<Valeur>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Valeur> valeurs = response.body();

                        // Associer le facteurId aux valeurs si nécessaire
                        for (Valeur valeur : valeurs) {
                            valeur.setFacteurId(facteurId); // Ajouter la relation
                        }

                        executor.execute(() -> {
                            try {
                                // Insérer les Valeurs dans la base locale
                                database.valeurDao().insertAll(valeurs);
                                Log.d("Repository", "Valeurs successfully inserted/updated.");

                                // Charger les Valeurs locales
                                List<Valeur> localValeurs = database.valeurDao().getValeursByFacteurId(facteurId);
                                liveData.postValue(localValeurs);
                            } catch (Exception e) {
                                Log.e("Repository", "Error inserting Valeurs", e);
                            }
                        });
                    } else {
                        Log.e("Repository", "API response for Valeurs unsuccessful.");
                        loadValeursFromLocalDatabase(facteurId, liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<Valeur>> call, Throwable t) {
                    Log.e("Repository", "Failed to fetch Valeurs from API", t);
                    loadValeursFromLocalDatabase(facteurId, liveData);
                }
            });
        } else {
            Log.d("Repository", "No network available, loading Valeurs from local database.");
            loadValeursFromLocalDatabase(facteurId, liveData);
        }

        return liveData;
    }

    private void loadValeursFromLocalDatabase(long facteurId, MutableLiveData<List<Valeur>> liveData) {
        executor.execute(() -> {
            try {
                List<Valeur> localValeurs = database.valeurDao().getValeursByFacteurId(facteurId);
                liveData.postValue(localValeurs);
            } catch (Exception e) {
                Log.e("Repository", "Error loading Valeurs from local database", e);
            }
        });
    }



    public LiveData<Facteur> getFacteurById(long facteurId) {
        MutableLiveData<Facteur> liveData = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getFacteurById(facteurId).enqueue(new Callback<Facteur>() {
                @Override
                public void onResponse(Call<Facteur> call, Response<Facteur> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Facteur facteur = response.body();

                        executor.execute(() -> {
                            try {
                                database.facteurDao().insert(facteur); // Mise à jour locale
                                Log.d("Repository", "Facteur successfully inserted/updated.");
                            } catch (Exception e) {
                                Log.e("Repository", "Error inserting Facteur", e);
                            }

                            Facteur localFacteur = database.facteurDao().getFacteurById(facteurId);
                            liveData.postValue(localFacteur);
                        });
                    } else {
                        Log.e("Repository", "API response for Facteur unsuccessful.");
                        loadFacteurFromLocalDatabase(facteurId, liveData);
                    }
                }

                @Override
                public void onFailure(Call<Facteur> call, Throwable t) {
                    Log.e("Repository", "Failed to fetch Facteur from API", t);
                    loadFacteurFromLocalDatabase(facteurId, liveData);
                }
            });
        } else {
            Log.d("Repository", "No network available, loading Facteur from local database.");
            loadFacteurFromLocalDatabase(facteurId, liveData);
        }

        return liveData;
    }

    private void loadFacteurFromLocalDatabase(long facteurId, MutableLiveData<Facteur> liveData) {
        executor.execute(() -> {
            Facteur localFacteur = database.facteurDao().getFacteurById(facteurId);
            liveData.postValue(localFacteur);
        });
    }


    public LiveData<EvaluationSiteWithDetailsDTO> createEvaluationSite(EvaluationSiteWithDetailsDTO evaluationSite) {
        MutableLiveData<EvaluationSiteWithDetailsDTO> createdEvaluationSite = new MutableLiveData<>();

        tokenRefresherRepository.refreshTokens(new TokenRefresherRepository.TokenRefreshCallback() {
            @Override
            public void onTokensRefreshed() {
                String accessTokenCookie = "accessTokenCookie=" + TokenManager.getInstance().getAccessToken();
                String xsrfTokenCookie = "XSRF-TOKEN=" + TokenManager.getInstance().getXsrfToken();
                String cookies = xsrfTokenCookie + "; " + accessTokenCookie;
                System.out.println("New access token: " + accessTokenCookie);
                System.out.println("New xsrf token: " + xsrfTokenCookie);
                apiService.createEvaluationSite(evaluationSite).enqueue(new Callback<EvaluationSiteWithDetailsDTO>() {
                    @Override
                    public void onResponse(Call<EvaluationSiteWithDetailsDTO> call, Response<EvaluationSiteWithDetailsDTO> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            createdEvaluationSite.setValue(response.body());
                        } else {
                            createdEvaluationSite.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<EvaluationSiteWithDetailsDTO> call, Throwable t) {
                        createdEvaluationSite.setValue(null);
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure() {
                createdEvaluationSite.setValue(null);
            }

        });
        return createdEvaluationSite;
    }

    // Add this method to EvaluationSiteRepository
    public LiveData<Boolean> deleteEvaluationSite(long evaluationSiteId) {
        MutableLiveData<Boolean> deleteResult = new MutableLiveData<>();

        tokenRefresherRepository.refreshTokens(new TokenRefresherRepository.TokenRefreshCallback() {
            @Override
            public void onTokensRefreshed() {
                apiService.deleteEvaluationSite(evaluationSiteId).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        deleteResult.setValue(response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        deleteResult.setValue(false);
                    }
                });
            }

            @Override
            public void onFailure() {
                deleteResult.setValue(false);
            }
        });

        return deleteResult;
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

                        Log.d("EvaluationSiteRepository", "API response received, processing DTOs...");

                        for (EvaluationSiteWithDetailsDTO dto : dtoList) {
                            try {
                                // Log raw DTO data
                                Log.d("EvaluationSiteRepository", "Processing DTO: " + dto);

                                // Map DTO to model and add to respective lists
                                evaluationSites.add(dto.toEvaluationSite());
                                evaluations.add(dto.getEvaluation().toEvaluation());
                                matrices.add(dto.getEvaluation().getMatrice());
                                if (dto.getEvaluation().getOrigine() != null) {
                                    origines.add(dto.getEvaluation().getOrigine());
                                }
                                sites.add(dto.getSite());
                            } catch (Exception e) {
                                Log.e("EvaluationSiteRepository", "Error processing DTO: " + dto, e);
                            }
                        }

                        // Save data to the local database
                        executor.execute(() -> {
                            try {
                                Log.d("EvaluationSiteRepository", "Inserting data into local database...");

                                database.matriceDao().insertAll(matrices);
                                database.origineDao().insertAll(origines);
                                database.siteDao().insertAll(sites);
                                database.evaluationDao().insertAll(evaluations);
                                database.evaluationSiteDao().insertAll(evaluationSites);

                                Log.d("EvaluationSiteRepository", "Data successfully inserted into local database.");
                            } catch (Exception e) {
                                Log.e("EvaluationSiteRepository", "Error inserting data into local database", e);
                            }

                            // Load updated data from local database
                            try {
                                List<EvaluationSiteWithDetails> details = database.evaluationSiteDao().getAllEvaluationSitesWithDetailsSync();
                                Log.d("EvaluationSiteRepository", "Loaded details from local database: " + details);
                                liveData.postValue(details);
                            } catch (Exception e) {
                                Log.e("EvaluationSiteRepository", "Error loading details from local database", e);
                            }
                        });
                    } else {
                        Log.e("EvaluationSiteRepository", "API response unsuccessful or body is null.");
                        loadLocalData(liveData);
                    }
                }

                @Override
                public void onFailure(Call<List<EvaluationSiteWithDetailsDTO>> call, Throwable t) {
                    Log.e("EvaluationSiteRepository", "API call failed", t);
                    loadLocalData(liveData);
                }
            });
        } else {
            Log.d("EvaluationSiteRepository", "No network available, loading from local database...");
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

    // EvaluationSiteRepository.java
    public LiveData<Boolean> synchronizeData() {
        MutableLiveData<Boolean> syncResult = new MutableLiveData<>();

        if (!isNetworkAvailable()) {
            Log.d("Repository", "No network available. Skipping synchronization.");
            syncResult.postValue(false);
            return syncResult;
        }

        executor.execute(() -> {
            try {
                Log.d("Repository", "Starting synchronization...");

                // Récupérer les données depuis l'API
                List<Site> sites = apiService.getAllSites().execute().body();
                List<Origine> origines = apiService.getAllOrigines().execute().body();
                List<Matrice> matrices = apiService.getAllMatrices().execute().body();
                List<EvaluationSiteWithDetailsDTO> evaluationSitesDto = apiService.getAllEvaluationSites().execute().body();

                if (sites == null || origines == null || matrices == null || evaluationSitesDto == null) {
                    Log.e("Repository", "Failed to fetch some data from API. Aborting synchronization.");
                    syncResult.postValue(false);
                    return;
                }

                List<EvaluationSite> evaluationSites = new ArrayList<>();
                List<Evaluation> evaluations = new ArrayList<>();
                List<Facteur> allFacteurs = new ArrayList<>();
                List<MatriceFacteur> allMatriceFacteurs = new ArrayList<>();
                List<Valeur> allValeurs = new ArrayList<>();

                // Traiter les EvaluationSites et Evaluations
                for (EvaluationSiteWithDetailsDTO dto : evaluationSitesDto) {
                    evaluationSites.add(dto.toEvaluationSite());
                    evaluations.add(dto.getEvaluation().toEvaluation());
                }

                // Traiter les Facteurs, MatriceFacteurs, et Valeurs
                for (Matrice matrice : matrices) {
                    List<MatriceFacteurDto> matriceFacteursDto = apiService.getMatriceFacteursByMatriceId(matrice.getId()).execute().body();
                    if (matriceFacteursDto != null) {
                        for (MatriceFacteurDto dto : matriceFacteursDto) {
                            Facteur facteur = dto.getFacteur();
                            if (facteur != null) {
                                allFacteurs.add(facteur);

                                // Ajouter la relation dans MatriceFacteur
                                MatriceFacteur matriceFacteur = new MatriceFacteur();
                                matriceFacteur.setMatriceId(matrice.getId());
                                matriceFacteur.setFacteurId(facteur.getId());
                                allMatriceFacteurs.add(matriceFacteur);

                                // Récupérer les valeurs pour le facteur
                                List<Valeur> valeurs = apiService.getValeursByFacteurId(facteur.getId()).execute().body();
                                if (valeurs != null) {
                                    for (Valeur valeur : valeurs) {
                                        valeur.setFacteurId(facteur.getId());
                                    }
                                    allValeurs.addAll(valeurs);
                                }
                            }
                        }
                    }
                }

                // Insertion Transactionnelle
                database.runInTransaction(() -> {
                    try {
                        Log.d("Repository", "Inserting data into local database...");

                        // Insérer les données principales
                        database.siteDao().insertAll(sites);
                        database.origineDao().insertAll(origines);
                        database.matriceDao().insertAll(matrices);

                        // Insérer les facteurs, relations MatriceFacteur, et valeurs
                        database.facteurDao().insertAll(allFacteurs);
                        database.matriceFacteurDao().insertAll(allMatriceFacteurs);
                        database.valeurDao().insertAll(allValeurs);

                        // Insérer les évaluations et les relations site-évaluation
                        database.evaluationDao().insertAll(evaluations);
                        database.evaluationSiteDao().insertAll(evaluationSites);

                        Log.d("Repository", "Data successfully inserted into local database.");
                    } catch (Exception e) {
                        Log.e("Repository", "Error during database insertion", e);
                        throw e; // Annule la transaction
                    }
                });

                Log.d("Repository", "Synchronization completed successfully.");
                syncResult.postValue(true);
            } catch (Exception e) {
                Log.e("Repository", "Error during synchronization", e);
                syncResult.postValue(false);
            }
        });

        return syncResult;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


