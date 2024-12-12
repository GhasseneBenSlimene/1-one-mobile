package com.example.one_mobile.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.repository.EvaluationSiteRepository;

import java.util.List;

public class EvaluationSiteViewModel extends ViewModel {

    private final EvaluationSiteRepository repository;
    private MutableLiveData<EvaluationSite> createdEvaluationSite;

    public EvaluationSiteViewModel() {
        repository = new EvaluationSiteRepository();
        createdEvaluationSite = new MutableLiveData<>();
    }

    public LiveData<List<Site>> getAllSites() {
        return repository.getAllSites();
    }

    public LiveData<List<Matrice>> getAllMatrices() {
        return repository.getAllMatrices();
    }

    public LiveData<List<MatriceFacteur>> getMatriceFacteursByMatriceId(long matriceId) {
        return repository.getMatriceFacteursByMatriceId(matriceId);
    }

    public LiveData<List<Valeur>> getValeursByFacteurId(long facteurId) {
        return repository.getValeursByFacteurId(facteurId);
    }

    public LiveData<Facteur> getFacteurById(long facteurId) {
        return repository.getFacteurById(facteurId);
    }

    public void createEvaluationSite(EvaluationSite evaluationSite) {
        repository.createEvaluationSite(evaluationSite).observeForever(newEvaluationSite -> {
            if (newEvaluationSite != null) {
                createdEvaluationSite.setValue(newEvaluationSite);
            }
        });
    }

    public LiveData<EvaluationSite> getCreatedEvaluationSite() {
        return createdEvaluationSite;
    }
}
