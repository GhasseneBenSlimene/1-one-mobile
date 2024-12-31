package com.example.one_mobile.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Origine;

import java.util.List;

@Dao
public interface OrigineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrigine(Origine origine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Origine> origines);

    @Query("SELECT * FROM origines WHERE id = :id")
    Origine getOrigineById(long id);

    @Query("SELECT * FROM origines")
    List<Origine> getAllOrigines();

    @Query("DELETE FROM origines")
    void clearAll();
}
