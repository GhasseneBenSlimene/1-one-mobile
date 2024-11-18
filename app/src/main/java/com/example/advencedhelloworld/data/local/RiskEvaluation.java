package com.example.advencedhelloworld.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "risk_evaluations")
public class RiskEvaluation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int riskLevel;

    public RiskEvaluation(String title, String description, int riskLevel) {
        this.title = title;
        this.description = description;
        this.riskLevel = riskLevel;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setId(int id) {
        this.id = id;
    }
}