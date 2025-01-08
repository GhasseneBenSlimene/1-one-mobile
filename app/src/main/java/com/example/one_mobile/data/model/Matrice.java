package com.example.one_mobile.data.model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "matrices")
public class Matrice {
    @PrimaryKey
    private long id;
    private String code;
    private String lib;
    private String regle;
    @Nullable
    private Boolean stage;
    @Nullable
    private Boolean calcIndice;
    private String entityName;

    // Getters and Setters
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

    @Nullable
    public Boolean getStage() {
        return stage;
    }

    public void setStage(@Nullable Boolean stage) {
        this.stage = stage;
    }

    @Nullable
    public Boolean getCalcIndice() {
        return calcIndice;
    }

    public void setCalcIndice(@Nullable Boolean calcIndice) {
        this.calcIndice = calcIndice;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return "Matrice{" +
                "id=" + id +
                ", regle='" + regle + '\'' +
                '}';
    }
}