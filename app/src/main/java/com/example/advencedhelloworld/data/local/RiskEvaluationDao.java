package com.example.advencedhelloworld.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RiskEvaluationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RiskEvaluation> risks);

    @Query("SELECT * FROM risk_evaluations")
    LiveData<List<RiskEvaluation>> getAllRisks();
}