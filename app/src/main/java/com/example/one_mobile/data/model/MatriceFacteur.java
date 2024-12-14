package com.example.one_mobile.data.model;

public class MatriceFacteur {
    private long id;
    private Facteur facteur;
    private Matrice matrice;
    private long order;
    private boolean requis;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Facteur getFacteur() {
        return facteur;
    }

    public void setFacteur(Facteur facteur) {
        this.facteur = facteur;
    }

    public Matrice getMatrice() {
        return matrice;
    }

    public void setMatrice(Matrice matrice) {
        this.matrice = matrice;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public boolean isRequis() {
        return requis;
    }

    public void setRequis(boolean requis) {
        this.requis = requis;
    }
}
