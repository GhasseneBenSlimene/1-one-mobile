package com.example.one_mobile.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Evaluation;

import java.util.List;

@Dao
public interface EvaluationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvaluation(Evaluation evaluation);

    @Query("SELECT * FROM evaluations WHERE id = :id")
    Evaluation getEvaluationById(long id);

    @Query("SELECT * FROM evaluations")
    List<Evaluation> getAllEvaluations();
}
