package com.example.one_mobile.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sync_queue")
public class SyncQueue {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String action;  // "CREATE", "UPDATE", "DELETE"
    private String tableName;  // "risk_evaluations"
    private String payload;  // Données modifiées en JSON

    // Constructeur
    public SyncQueue(String action, String tableName, String payload) {
        this.action = action;
        this.tableName = tableName;
        this.payload = payload;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
