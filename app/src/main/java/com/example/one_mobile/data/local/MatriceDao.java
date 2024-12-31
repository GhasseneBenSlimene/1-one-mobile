package com.example.one_mobile.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Matrice;

import java.util.List;

@Dao
public interface MatriceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMatrice(Matrice matrice);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Matrice> matrices);

    @Query("SELECT * FROM matrices WHERE id = :id")
    Matrice getMatriceById(long id);

    @Query("SELECT * FROM matrices")
    List<Matrice> getAllMatrices();

    @Query("DELETE FROM matrices")
    void clearAll();
}
