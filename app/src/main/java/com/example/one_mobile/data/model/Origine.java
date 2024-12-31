package com.example.one_mobile.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "origines")
public class Origine {
    @PrimaryKey
    private long id;
    private String lib;
    private String code; // Champ ajouté

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
