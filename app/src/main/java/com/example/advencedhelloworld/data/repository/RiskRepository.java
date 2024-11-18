package com.example.advencedhelloworld.data.repository;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.advencedhelloworld.data.local.AppDatabase;
import com.example.advencedhelloworld.data.local.RiskEvaluation;
import com.example.advencedhelloworld.data.local.RiskEvaluationDao;
import com.example.advencedhelloworld.data.network.ApiService;
import com.example.advencedhelloworld.data.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RiskRepository {
    private final RiskEvaluationDao riskDao;
    private final ApiService apiService;
    private final Application application;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public RiskRepository(Application application) {
        this.application = application;
        AppDatabase db = AppDatabase.getInstance(application);
        riskDao = db.riskEvaluationDao();
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<RiskEvaluation>> getRisks() {
        MutableLiveData<List<RiskEvaluation>> data = new MutableLiveData<>();

        if (isNetworkAvailable()) {
            apiService.getRisks().enqueue(new Callback<List<RiskEvaluation>>() {
                @Override
                public void onResponse(Call<List<RiskEvaluation>> call, Response<List<RiskEvaluation>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        data.setValue(response.body());
                        // Insérer les données en arrière-plan pour éviter de bloquer le thread principal
                        executor.execute(() -> riskDao.insertAll(response.body()));
                    }
                }

                @Override
                public void onFailure(Call<List<RiskEvaluation>> call, Throwable t) {
                    loadLocalData(data);
                }
            });
        } else {
            loadLocalData(data);
        }

        return data;
    }

    private void loadLocalData(MutableLiveData<List<RiskEvaluation>> data) {
        riskDao.getAllRisks().observeForever(data::setValue);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
