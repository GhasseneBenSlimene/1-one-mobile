package com.example.one_mobile.data.model;

import androidx.room.Entity;

@Entity(tableName = "matrices")
public class Matrice {
    private long id;
    private String code;
    private String lib;
    private String regle;
    private boolean stage;
    private boolean calcIndice;
    private String entityName;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getRegle() {
        return regle;
    }

    public void setRegle(String regle) {
        this.regle = regle;
    }

    public boolean isStage() {
        return stage;
    }

    public void setStage(boolean stage) {
        this.stage = stage;
    }

    public boolean isCalcIndice() {
        return calcIndice;
    }

    public void setCalcIndice(boolean calcIndice) {
        this.calcIndice = calcIndice;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
