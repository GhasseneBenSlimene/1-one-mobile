package com.example.one_mobile.data.local.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Facteur;

import java.util.List;

@Dao
public interface FacteurDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Facteur facteur);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Facteur> facteurs);

    @Query("SELECT * FROM facteurs")
    List<Facteur> getAllFacteursSync();

    @Query("SELECT * FROM facteurs WHERE id IN (:facteurIds)")
    List<Facteur> getFacteursByIds(List<Long> facteurIds);

    @Query("SELECT * FROM facteurs WHERE matrice_id = :matriceId")
    List<Facteur> getFacteursByMatriceId(long matriceId);

    @Query("SELECT * FROM facteurs WHERE id = :facteurId")
    Facteur getFacteurById(long facteurId);

    @Query("DELETE FROM facteurs")
    void clearAll();
}

