package com.example.one_mobile.data.local.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.one_mobile.data.model.PendingRequest;

import java.util.List;

@Dao
public interface PendingRequestDao {

    @Insert
    void insert(PendingRequest pendingRequest);

    @Query("DELETE FROM pending_requests WHERE id = :id")
    void deleteById(long id);

    @Query("SELECT * FROM pending_requests ORDER BY timestamp ASC")
    List<PendingRequest> getAll();
}
