package com.example.one_mobile.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.one_mobile.data.local.Dto.EvaluationDTO;
import com.example.one_mobile.data.local.Dto.EvaluationSiteWithDetailsDTO;
import com.example.one_mobile.data.model.Evaluation;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Risque;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.data.repository.EvaluationSiteRepository;

import java.util.List;

public class EvaluationSiteViewModel extends ViewModel {

    private final EvaluationSiteRepository repository;
    private final MutableLiveData<EvaluationSiteWithDetailsDTO> createdEvaluationSite;

    public EvaluationSiteViewModel(Context context) {
        repository = new EvaluationSiteRepository(context);
        createdEvaluationSite = new MutableLiveData<>();
    }

    // Récupérer tous les EvaluationSites avec leurs détails
    public LiveData<List<EvaluationSiteWithDetails>> getAllEvaluationSites() {
        return repository.getAllEvaluationSites();
    }

    public LiveData<EvaluationSite> getEvaluationSiteById(long evaluationSiteId) {
        return repository.loadEvaluationSiteByIdFromLocalDatabase(evaluationSiteId);
    }

    public LiveData<Boolean> clearDatabase() {
        return repository.clearDatabase();
    }

    public LiveData<EvaluationDTO> getEvaluationDTOByEvaluation(Evaluation evaluation) {
        return repository.getEvaluationDTOByEvaluation(evaluation);
    }

    public LiveData<EvaluationSiteWithDetailsDTO> getEvaluationSiteWithDetailsDTOById(long evaluationSiteId) {
        return repository.getEvaluationSiteWithDetailsDTOById(evaluationSiteId);
    }


    public boolean clearDatabaseSync() {
        return repository.clearDatabaseSync();
    }

    // Gestion des Sites
    public LiveData<List<Site>> getAllSites() {
        return repository.getAllSites();
    }

    public LiveData<List<Risque>> getAllRisques() {
        return repository.getAllRisques();
    }


    // Gestion des Origines
    public LiveData<List<Origine>> getAllOrigines() {
        return repository.getAllOrigines();
    }

    // Gestion des Matrices
    public LiveData<List<Matrice>> getAllMatrices() {
        return repository.getAllMatrices();
    }

    // Gestion des Facteurs
    public LiveData<List<Facteur>> getFacteursByMatriceId(long matriceId) {
        return repository.getFacteursByMatriceId(matriceId);
    }

    // Gestion des Valeurs
    public LiveData<List<Valeur>> getValeursByFacteurId(long facteurId) {
        return repository.getValeursByFacteurId(facteurId);
    }

    // Gestion des Facteurs
    public LiveData<Facteur> getFacteurById(long facteurId) {
        return repository.getFacteurById(facteurId);
    }

    public LiveData<Boolean> synchronizeData() {
        return repository.synchronizeData();
    }

    public LiveData<EvaluationSiteWithDetailsDTO> createEvaluationSite(EvaluationSiteWithDetailsDTO evaluationSite) {
        LiveData<EvaluationSiteWithDetailsDTO> result = repository.createEvaluationSite(evaluationSite);
        result.observeForever(new Observer<EvaluationSiteWithDetailsDTO>() {
            @Override
            public void onChanged(EvaluationSiteWithDetailsDTO evaluationSite) {
                createdEvaluationSite.setValue(evaluationSite);
            }
        });
        return createdEvaluationSite;
    }

    public LiveData<EvaluationSiteWithDetailsDTO> updateEvaluationSite(long id, EvaluationSiteWithDetailsDTO evaluationSite) {
        return repository.updateEvaluationSite(id, evaluationSite);
    }

    public LiveData<Boolean> deleteEvaluationSite(long evaluationSiteId) {
        return repository.deleteEvaluationSite(evaluationSiteId);
    }

    public LiveData<EvaluationSiteWithDetailsDTO> getCreatedEvaluationSite() {
        return createdEvaluationSite;
    }
}
