package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class EvaluationRisqueActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_risque);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Évaluation du risque par Unité de travail");
        }

        tableLayout = findViewById(R.id.table_evaluation_risque);
        Button buttonAjouter = findViewById(R.id.button_ajouter);

        buttonAjouter.setOnClickListener(v -> openAddDialog());
    }

    private void openAddDialog() {
        // Fonctionnalité à ajouter plus tard pour le bouton "Ajouter"
        Intent intent = new Intent(EvaluationRisqueActivity.this, AjouterEvaluationUniteActivity.class);
        startActivity(intent);
    }
}
