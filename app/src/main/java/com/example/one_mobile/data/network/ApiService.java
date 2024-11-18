package com.example.one_mobile.data.network;

import com.example.one_mobile.data.local.RiskEvaluation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {
    @GET("risks/sync")
    Call<List<RiskEvaluation>> getModifiedRisks(@Query("lastSync") String lastSyncTimestamp);

    @POST("risks")
    Call<RiskEvaluation> addRisk(@Body RiskEvaluation risk);

    @PUT("risks")
    Call<Void> updateRisk(@Body RiskEvaluation risk);
}
