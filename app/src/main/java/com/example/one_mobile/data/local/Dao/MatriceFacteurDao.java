package com.example.one_mobile.data.local.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;

import java.util.List;

@Dao
public interface MatriceFacteurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MatriceFacteur> matriceFacteurs);

    @Query("SELECT * FROM facteurs WHERE id IN (SELECT facteurId FROM MatriceFacteur WHERE matriceId = :matriceId)")
    List<Facteur> getFacteursByMatriceId(long matriceId);

    @Query("SELECT * FROM matrices WHERE id IN (SELECT matriceId FROM MatriceFacteur WHERE facteurId = :facteurId)")
    List<Matrice> getMatricesByFacteurId(long facteurId);

    @Query("DELETE FROM MatriceFacteur")
    void clearAll();
}