package com.example.one_mobile.data.model;

import androidx.room.Entity;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;

@Entity(tableName = "evaluation_sites")
public class EvaluationSite {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @Embedded
    private Site site;

    @Embedded(prefix = "evaluation_")
    private Evaluation evaluation;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //comment

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
