package com.example.one_mobile.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.one_mobile.data.model.Site;

import java.util.List;

@Dao
public interface SiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Site> sites);

    @Query("SELECT * FROM sites")
    LiveData<List<Site>> getAllSites();

    @Query("DELETE FROM sites")
    void clearAll();
}
