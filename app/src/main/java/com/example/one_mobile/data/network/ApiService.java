package com.example.one_mobile.data.network;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<Void> getSessionToken(); // Étape 1 : Récupérer les cookies avec le token XSRF

    @POST("/auth")
    Call<AuthResponse> authenticate(
            @Header("X-XSRF-TOKEN") String xsrfToken, // Inclure le token dans l'en-tête
            @Body AuthenticationRequest request // Envoyer les identifiants
    );
}
