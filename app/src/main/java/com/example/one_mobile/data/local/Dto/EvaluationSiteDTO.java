package com.example.one_mobile.data.local.Dto;

import com.example.one_mobile.data.model.Site;

public class EvaluationSiteDTO {
    private long id;
    private Site site;
    private EvaluationDTO evaluation;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public EvaluationDTO getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationDTO evaluation) {
        this.evaluation = evaluation;
    }
}
