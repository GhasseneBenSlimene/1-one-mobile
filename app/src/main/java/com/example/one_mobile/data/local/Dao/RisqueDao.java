package com.example.one_mobile.data.local.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Risque;

import java.util.List;

@Dao
public interface RisqueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Risque risque);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Risque> risques);

    @Query("SELECT * FROM risques WHERE id = :id")
    Risque getRisqueById(long id);

    @Query("SELECT * FROM risques")
    List<Risque> getAllRisques();

    @Query("DELETE FROM risques")
    void clearAll();
}