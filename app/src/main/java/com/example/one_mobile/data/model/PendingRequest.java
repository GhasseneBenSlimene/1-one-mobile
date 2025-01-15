package com.example.one_mobile.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pending_requests")
public class PendingRequest {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String type; // CREATE, DELETE
    private String entityType; // Par exemple: EvaluationSite
    private String payload; // JSON payload
    private long entityId; // ID de l'entité pour DELETE ou UPDATE

    private long timestamp; // Timestamp pour la priorisation

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
