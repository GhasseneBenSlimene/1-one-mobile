package com.example.one_mobile.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RiskEvaluationDao {
    @Insert
    void insert(RiskEvaluation risk);

    @Update
    void update(RiskEvaluation risk);

    @Query("DELETE FROM risk_evaluations WHERE id = :id")
    void deleteById(int id);

    @Delete
    void delete(RiskEvaluation risk);


    @Query("SELECT * FROM risk_evaluations")
    LiveData<List<RiskEvaluation>> getAllRisks();

    @Query("DELETE FROM risk_evaluations")
    void clearAll();

    @Query("SELECT EXISTS(SELECT 1 FROM risk_evaluations WHERE id = :id)")
    boolean exists(int id);
}
