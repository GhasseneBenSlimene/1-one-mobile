package com.example.one_mobile.data.local.Dto;

import com.example.one_mobile.data.model.Evaluation;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Risque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EvaluationDTO {
    private long id;
    private Origine origine;

    private Risque risque;
    private Matrice matrice;
    private float indice;
    private int indiceInt;
    private String desc;
    private String descCourt;
    private boolean valide;
    private String date;

    private String valid;

    // Getters and Setters
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

    public Risque getRisque() {
        return risque;
    }

    public void setRisque(Risque risque) {
        this.risque = risque;
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

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Evaluation toEvaluation() {
        Evaluation evaluation = new Evaluation();
        evaluation.setId(this.getId());
        evaluation.setOrigineId(this.getOrigine() != null ? this.getOrigine().getId() : null);
        evaluation.setMatriceId(this.getMatrice() != null ? this.getMatrice().getId() : 0);
        evaluation.setIndice(this.getIndice());
        evaluation.setIndiceInt(this.getIndiceInt());
        evaluation.setDesc(this.getDesc());
        evaluation.setDescCourt(this.getDescCourt());
        evaluation.setValide(this.isValide());

        String[] dateFormats = {
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd"
        };
        Date parsedDate = null;

        for (String format : dateFormats) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                parsedDate = dateFormat.parse(this.getDate());
                break;
            } catch (ParseException e) {
                // Continue to the next format
            }
        }

        evaluation.setDate(parsedDate);

        return evaluation;
    }

}
