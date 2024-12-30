package com.example.one_mobile.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Matrice;

import java.util.List;

@Dao
public interface MatriceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Matrice matrice);

    @Query("SELECT * FROM matrice")
    LiveData<List<Matrice>> getAllMatrices();

    @Query("SELECT * FROM matrice WHERE id = :id")
    LiveData<Matrice> getMatriceById(long id);
}