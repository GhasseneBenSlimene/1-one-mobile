package com.example.advencedhelloworld.data.network;

import com.example.advencedhelloworld.data.local.RiskEvaluation;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
public interface ApiService {
    @GET("risks")
    Call<List<RiskEvaluation>> getRisks();
}