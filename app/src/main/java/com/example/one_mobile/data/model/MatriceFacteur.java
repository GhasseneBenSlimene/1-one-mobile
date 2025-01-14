package com.example.one_mobile.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "matrice_facteurs",
        foreignKeys = {
                @ForeignKey(entity = Matrice.class,
                        parentColumns = "id",
                        childColumns = "matrice_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Facteur.class,
                        parentColumns = "id",
                        childColumns = "facteur_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("matrice_id"), @Index("facteur_id")}
)
public class MatriceFacteur {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "matrice_id")
    private long matriceId;

    @ColumnInfo(name = "facteur_id")
    private long facteurId;

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMatriceId() {
        return matriceId;
    }

    public void setMatriceId(long matriceId) {
        this.matriceId = matriceId;
    }

    public long getFacteurId() {
        return facteurId;
    }

    public void setFacteurId(long facteurId) {
        this.facteurId = facteurId;
    }
}
