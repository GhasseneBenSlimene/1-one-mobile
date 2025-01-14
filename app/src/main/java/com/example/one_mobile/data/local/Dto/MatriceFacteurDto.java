package com.example.one_mobile.data.local.Dto;

import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;

public class MatriceFacteurDto {
    private long id;
    private Facteur facteur;
    private Matrice matrice;

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

    public MatriceFacteur toMatriceFacteur() {
        MatriceFacteur matriceFacteur = new MatriceFacteur();
        matriceFacteur.setId(this.id);
        matriceFacteur.setFacteurId(this.facteur != null ? this.facteur.getId() : 0);
        matriceFacteur.setMatriceId(this.matrice != null ? this.matrice.getId() : 0);
        return matriceFacteur;
    }
}
