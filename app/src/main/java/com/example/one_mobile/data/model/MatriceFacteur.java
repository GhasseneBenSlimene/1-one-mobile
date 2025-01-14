package com.example.one_mobile.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"matriceId", "facteurId"})
public class MatriceFacteur {
    @ColumnInfo(name = "matriceId")
    private long matriceId;

    @ColumnInfo(name = "facteurId")
    private long facteurId;

    // Getters and Setters
    public long getMatriceId() {
        return matriceId;
    }

    public void setMatriceId(long matriceId) {
        this.matriceId = matriceId;
    }

    public long getFacteurId() {
        return facteurId;
    }

    public void setFacteurId(long facteurId) {
        this.facteurId = facteurId;
    }
}
