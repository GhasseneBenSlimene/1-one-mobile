package com.example.one_mobile.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "valeurs",
        foreignKeys = @ForeignKey(
                entity = Facteur.class,
                parentColumns = "id",
                childColumns = "facteur_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("facteur_id")}
)
public class Valeur {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "facteur_id")
    private long facteurId;

    private String code;

    private String lib;

    private float value;

    private String desc;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFacteurId() {
        return facteurId;
    }

    public void setFacteurId(long facteurId) {
        this.facteurId = facteurId;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
