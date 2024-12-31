package com.example.one_mobile.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.EvaluationSite;

import java.util.List;

@Dao
public interface EvaluationSiteDao {

    @Query("SELECT * FROM evaluation_sites")
    LiveData<List<EvaluationSite>> getAllEvaluationSites();

    @Query("SELECT * FROM evaluation_sites")
    List<EvaluationSite> getAllEvaluationSitesSync();

    @Query("DELETE FROM evaluation_sites")
    void clearAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EvaluationSite> evaluationSites);
}

