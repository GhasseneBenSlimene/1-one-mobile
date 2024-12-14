package com.example.one_mobile.data.network;

import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.model.EvaluationValeur;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

// RiskEvaluation
import com.example.one_mobile.data.model.RiskEvaluation;

// Authentication
import com.example.one_mobile.data.model.AuthResponse;
import com.example.one_mobile.data.model.AuthenticationRequest;


import java.util.List;

public interface ApiService {
    @GET("risks/sync")
    Call<List<RiskEvaluation>> getModifiedRisks(@Query("lastSync") String lastSyncTimestamp);

    @POST("risks")
    Call<RiskEvaluation> addRisk(@Body RiskEvaluation risk);

    @PUT("risks")
    Call<Void> updateRisk(@Body RiskEvaluation risk);

    // Authentication
    @GET("/auth/openSession")
    Call<Void> getSessionToken();

    @POST("/auth")
    Call<AuthResponse> authenticate(
            @Header("X-XSRF-TOKEN") String xsrfToken,
            @Body AuthenticationRequest request
    );

    // EvaluationSite
    @GET("/evaluationSite/")
    Call<List<EvaluationSite>> getAllEvaluationSites();

    @POST("/evaluationSite/")
    Call<EvaluationSite> createEvaluationSite(@Body EvaluationSite evaluationSite);

    // Matrice
    @GET("/Matrice/")
    Call<List<Matrice>> getAllMatrices();

    @GET("/Matrice/{id}")
    Call<Matrice> getMatriceById(@Path("id") long matriceId);

    // Site
    @GET("/Site/")
    Call<List<Site>> getAllSites();

    @GET("/Site/{id}")
    Call<Site> getSiteById(@Path("id") long siteId);

    // Facteurs et Valeurs
    @GET("/facteur/matrice/{id}")
    Call<List<MatriceFacteur>> getMatriceFacteursByMatriceId(@Path("id") long matriceId);

    @GET("/Facteur/{id}")
    Call<Facteur> getFacteurById(@Path("id") long facteurId);

    @GET("/valeur/facteur/{id}")
    Call<List<Valeur>> getValeursByFacteurId(@Path("id") long facteurId);

    // EvaluationValeur
    @POST("/evaluationValeur/")
    Call<EvaluationValeur> createEvaluationValeur(@Body EvaluationValeur evaluationValeur);

    @GET("/evaluationValeur/evaluation/{id}")
    Call<List<EvaluationValeur>> getEvaluationValeursByEvaluationId(@Path("id") long evaluationId);
}
