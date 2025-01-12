package com.example.one_mobile.data.local.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.MatriceFacteur;

import java.util.List;

@Dao
public interface MatriceFacteurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MatriceFacteur matriceFacteur);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MatriceFacteur> matriceFacteurs);

    @Query("SELECT * FROM matrice_facteurs WHERE matrice_id = :matriceId")
    List<MatriceFacteur> getFacteursByMatriceId(long matriceId);

    @Query("SELECT * FROM matrice_facteurs WHERE facteur_id = :facteurId")
    List<MatriceFacteur> getMatricesByFacteurId(long facteurId);

    @Query("DELETE FROM matrice_facteurs WHERE matrice_id = :matriceId")
    void deleteByMatriceId(long matriceId);

    @Query("DELETE FROM matrice_facteurs WHERE facteur_id = :facteurId")
    void deleteByFacteurId(long facteurId);

    @Query("DELETE FROM matrice_facteurs")
    void clearAll();
}
