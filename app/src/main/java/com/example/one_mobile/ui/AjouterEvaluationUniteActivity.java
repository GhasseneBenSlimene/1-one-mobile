package com.example.one_mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class AjouterEvaluationUniteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_evaluation_unite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ajouter une évaluation (Unité de travail)");
        }
    }
}
