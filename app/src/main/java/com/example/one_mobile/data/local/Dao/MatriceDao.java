package com.example.one_mobile.data.local.Dao;

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Matrice> matrices);

    @Query("SELECT * FROM matrices WHERE id = :id")
    Matrice getMatriceById(long id);

    @Query("SELECT * FROM matrices")
    LiveData<List<Matrice>> getAllMatrices();

    @Query("SELECT * FROM matrices")
    List<Matrice> getAllMatricesSync();

    @Query("DELETE FROM matrices")
    void clearAll();
}

