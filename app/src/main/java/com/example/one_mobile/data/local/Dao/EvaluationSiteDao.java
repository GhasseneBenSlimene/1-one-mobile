package com.example.one_mobile.data.local.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;

import java.util.List;

@Dao
public interface EvaluationSiteDao {

    // Insert a single EvaluationSite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EvaluationSite evaluationSite);

    // Insert multiple EvaluationSites
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EvaluationSite> evaluationSites);

    // Get all EvaluationSites with details
    @Transaction
    @Query("SELECT * FROM evaluation_sites")
    LiveData<List<EvaluationSiteWithDetails>> getAllEvaluationSitesWithDetails();

    // Get all EvaluationSites with details (synchronous)
    @Transaction
    @Query("SELECT * FROM evaluation_sites")
    List<EvaluationSiteWithDetails> getAllEvaluationSitesWithDetailsSync();

    // Get all EvaluationSites
    @Query("SELECT * FROM evaluation_sites")
    LiveData<List<EvaluationSite>> getAllEvaluationSites();

    // Get all EvaluationSites (synchronous)
    @Query("SELECT * FROM evaluation_sites")
    List<EvaluationSite> getAllEvaluationSitesSync();

    // Get a specific EvaluationSite by ID
    @Query("SELECT * FROM evaluation_sites WHERE id = :evaluationSiteId")
    EvaluationSite getEvaluationSiteById(long evaluationSiteId);

    // Clear all EvaluationSites
    @Query("DELETE FROM evaluation_sites")
    void clearAll();

    // Delete a specific EvaluationSite by ID
    @Query("DELETE FROM evaluation_sites WHERE id = :evaluationSiteId")
    void deleteById(long evaluationSiteId);

    // Get a specific EvaluationSite by ID with details
    @Transaction
    @Query("SELECT * FROM evaluation_sites WHERE id = :evaluationSiteId")
    LiveData<EvaluationSiteWithDetails> getEvaluationSiteWithDetailsById(long evaluationSiteId);

    // Get a specific EvaluationSite by ID with details (synchronous)
    @Transaction
    @Query("SELECT * FROM evaluation_sites WHERE id = :evaluationSiteId")
    EvaluationSiteWithDetails getEvaluationSiteWithDetailsByIdSync(long evaluationSiteId);
}

