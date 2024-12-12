package com.example.one_mobile.data.model;

public class EvaluationValeur {

    private boolean invalid;
    private String invalidMessage;
    private long id;
    private Valeur valeurByValIntId; // Référence vers une entité Valeur pour ValInt
    private Valeur valeurByValId;    // Référence vers une entité Valeur pour Val
    private Facteur facteur;         // Facteur associé à cette EvaluationValeur
    private Evaluation evaluation;   // Référence vers l'évaluation associée
    private float value;             // Valeur numérique associée au facteur
    private int valueInt;            // Valeur entière associée au facteur
    private long valeurIntId;        // Identifiant de la valeur entière
    private long valeurId;           // Identifiant de la valeur

    // Getters et Setters
    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public String getInvalidMessage() {
        return invalidMessage;
    }

    public void setInvalidMessage(String invalidMessage) {
        this.invalidMessage = invalidMessage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Valeur getValeurByValIntId() {
        return valeurByValIntId;
    }

    public void setValeurByValIntId(Valeur valeurByValIntId) {
        this.valeurByValIntId = valeurByValIntId;
    }

    public Valeur getValeurByValId() {
        return valeurByValId;
    }

    public void setValeurByValId(Valeur valeurByValId) {
        this.valeurByValId = valeurByValId;
    }

    public Facteur getFacteur() {
        return facteur;
    }

    public void setFacteur(Facteur facteur) {
        this.facteur = facteur;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }

    public long getValeurIntId() {
        return valeurIntId;
    }

    public void setValeurIntId(long valeurIntId) {
        this.valeurIntId = valeurIntId;
    }

    public long getValeurId() {
        return valeurId;
    }

    public void setValeurId(long valeurId) {
        this.valeurId = valeurId;
    }
}
