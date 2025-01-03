package com.example.one_mobile.data.model;

public class Evaluation {
    private long id;
    private Origine origine;
    private Matrice matrice;
    private float indice;
    private int indiceInt;
    private String desc;
    private String descCourt;
    private String valid;
    private String date;
    private Risque risque;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Origine getOrigine() {
        return origine;
    }

    public void setOrigine(Origine origine) {
        this.origine = origine;
    }

    public Matrice getMatrice() {
        return matrice;
    }

    public void setMatrice(Matrice matrice) {
        this.matrice = matrice;
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

    public Risque getRisque() {
        return risque;
    }

    public void setRisque(Risque risque) {
        this.risque = risque;
    }
}
