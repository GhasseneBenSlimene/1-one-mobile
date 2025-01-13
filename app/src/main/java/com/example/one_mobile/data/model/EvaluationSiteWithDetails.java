package com.example.one_mobile.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

@Entity
public class EvaluationSiteWithDetails {
    @Embedded
    private EvaluationSite evaluationSite;

    @Relation(parentColumn = "site_id", entityColumn = "id", entity = Site.class)
    private Site site;

    @Relation(parentColumn = "evaluation_id", entityColumn = "id", entity = Evaluation.class)
    private Evaluation evaluation;

    // Getters and Setters
    public EvaluationSite getEvaluationSite() {
        return evaluationSite;
    }

    public void setEvaluationSite(EvaluationSite evaluationSite) {
        this.evaluationSite = evaluationSite;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Evaluation   getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}