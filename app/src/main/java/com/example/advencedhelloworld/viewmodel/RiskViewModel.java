package com.example.advencedhelloworld.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.advencedhelloworld.data.local.RiskEvaluation;
import com.example.advencedhelloworld.data.repository.RiskRepository;

import java.util.List;

public class RiskViewModel extends AndroidViewModel {
    private final RiskRepository repository;
    private final LiveData<List<RiskEvaluation>> risks;

    public RiskViewModel(Application application) {
        super(application);
        repository = new RiskRepository(application);
        risks = repository.getRisks();  // Appelle le Repository pour obtenir les données
    }

    public LiveData<List<RiskEvaluation>> getRisks() {
        return risks;  // Expose les données sous forme de LiveData
    }
}
