package com.example.one_mobile.data.local;

import androidx.lifecycle.LiveData;
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

    @Query("SELECT * FROM matrice_facteur WHERE matrice_id = :matriceId")
    LiveData<List<MatriceFacteur>> getMatriceFacteursByMatriceId(long matriceId);
}