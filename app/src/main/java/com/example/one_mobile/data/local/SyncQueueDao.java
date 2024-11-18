package com.example.one_mobile.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SyncQueueDao {
    @Insert
    void insert(SyncQueue syncQueue);

    @Query("SELECT * FROM sync_queue")
    List<SyncQueue> getAllPendingSync();

    @Query("DELETE FROM sync_queue WHERE id = :id")
    void deleteById(int id);
}
