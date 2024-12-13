package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set the title dynamically
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Document Unique");
        }
        // Élément cliquable pour Évaluation du risque
        TextView itemEvaluationRisque = findViewById(R.id.item_evaluation_risque);
        itemEvaluationRisque.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EvaluationRisqueActivity.class);
            startActivity(intent);
        });
        // Élément cliquable pour Évaluation du risque par poste
        TextView itemEvaluationRisquePoste = findViewById(R.id.item_evaluation_risque_poste);
        itemEvaluationRisquePoste.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EvaluationRisquePosteActivity.class);
            startActivity(intent);
        });
        // Élément cliquable pour Évaluation du risque par action
        TextView itemEvaluationRisqueAction = findViewById(R.id.item_evaluation_risque_action);
        itemEvaluationRisqueAction.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EvaluationRisqueActionActivity.class);
            startActivity(intent);
        });
        // Élément cliquable pour Évaluation du risque par site
        TextView itemEvaluationRisqueSite = findViewById(R.id.item_evaluation_risque_site);
        itemEvaluationRisqueSite.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EvaluationRisqueSiteActivity.class);
            startActivity(intent);
        });
        // Élément cliquable pour Plans d'action par Unité de travail
        TextView itemPlansAction = findViewById(R.id.item_plans_action);
        itemPlansAction.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlansActionUniteActivity.class);
            startActivity(intent);
        });

    }
}
