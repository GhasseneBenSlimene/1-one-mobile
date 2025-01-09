package com.example.one_mobile.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "evaluations",
        foreignKeys = {
                @ForeignKey(entity = Origine.class, parentColumns = "id", childColumns = "origine_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Matrice.class, parentColumns = "id", childColumns = "matrice_id", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("origine_id"), @Index("matrice_id")}
)
public class Evaluation {
    @PrimaryKey
    private long id;

    @ColumnInfo(name = "origine_id", defaultValue = "NULL")
    private Long origineId; // Use Long instead of long to allow null values

    @ColumnInfo(name = "matrice_id")
    private long matriceId;

    private Origine origine;
    private Matrice matrice;
    private float indice;
    private int indiceInt;
    private String desc;
    private String descCourt;
    private boolean valide;
    private Date date;
    private String valid;
    private String date;
    private Risque risque;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getOrigineId() {
        return origineId;
    public Origine getOrigine() {
        return origine;
    }

    public void setOrigineId(Long origineId) {
        this.origineId = origineId;
    public void setOrigine(Origine origine) {
        this.origine = origine;
    }

    public long getMatriceId() {
        return matriceId;
    }

    public void setMatriceId(long matriceId) {
        this.matriceId = matriceId;
    }

    public float getIndice() {
        return indice;
    }

    public void setIndice(float indice) {
        this.indice = indice;
    }

    public int getIndiceInt() {
        return indiceInt;
    }

    public void setIndiceInt(int indiceInt) {
        this.indiceInt = indiceInt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescCourt() {
        return descCourt;
    }

    public void setDescCourt(String descCourt) {
        this.descCourt = descCourt;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", origineId=" + origineId +
                ", matriceId=" + matriceId +
                ", indice=" + indice +
                ", indiceInt=" + indiceInt +
                ", desc='" + desc + '\'' +
                ", descCourt='" + descCourt + '\'' +
                ", valide=" + valide +
                ", date=" + date +
                '}';
    }
}