package com.example.one_mobile.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "facteurs")
public class Facteur {
    @PrimaryKey
    private long id;
    private String code;
    private String lib;
    private float type;

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

    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }
}