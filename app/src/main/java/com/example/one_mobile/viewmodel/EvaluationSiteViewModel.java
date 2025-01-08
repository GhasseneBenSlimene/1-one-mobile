package com.example.one_mobile.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.repository.EvaluationSiteRepository;

import java.util.List;

public class EvaluationSiteViewModel extends ViewModel {

    private final EvaluationSiteRepository repository;
    private MutableLiveData<EvaluationSite> createdEvaluationSite;

    public EvaluationSiteViewModel(Context context) {
        repository = new EvaluationSiteRepository(context);
        createdEvaluationSite = new MutableLiveData<>();
    }

    // Récupérer tous les EvaluationSites avec leurs détails
    public LiveData<List<EvaluationSiteWithDetails>> getAllEvaluationSites() {
        return repository.getAllEvaluationSites();
    }

    // Création d'un nouveau EvaluationSite
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

    // Gestion des Sites
    public LiveData<List<Site>> getAllSites() {
        return repository.getAllSites();
    }

    // Gestion des Origines
    public LiveData<List<Origine>> getAllOrigines() {
        return repository.getAllOrigines();
    }

    // Gestion des Matrices
    public LiveData<List<Matrice>> getAllMatrices() {
        return repository.getAllMatrices();
    }

    // Gestion des MatriceFacteurs
    public LiveData<List<MatriceFacteur>> getMatriceFacteursByMatriceId(long matriceId) {
        return repository.getMatriceFacteursByMatriceId(matriceId);
    }

    // Gestion des Valeurs
    public LiveData<List<Valeur>> getValeursByFacteurId(long facteurId) {
        return repository.getValeursByFacteurId(facteurId);
    }

    // Gestion des Facteurs
    public LiveData<Facteur> getFacteurById(long facteurId) {
        return repository.getFacteurById(facteurId);
    }
}
