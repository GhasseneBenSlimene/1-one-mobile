package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.one_mobile.R;

public class Home extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Set the title dynamically
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Document Unique");
        }
        // Élément cliquable pour Évaluation du risque
        TextView itemEvaluationRisque = findViewById(R.id.item_evaluation_risque);
        itemEvaluationRisque.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, EvaluationListByUtr.class);
            startActivity(intent);
        });
        // Élément cliquable pour Évaluation du risque par poste
        TextView itemEvaluationRisquePoste = findViewById(R.id.item_evaluation_risque_poste);
        itemEvaluationRisquePoste.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, EvaluationListByPost.class);
            startActivity(intent);
        });
        // Élément cliquable pour Évaluation du risque par action
        TextView itemEvaluationRisqueAction = findViewById(R.id.item_evaluation_risque_action);
        itemEvaluationRisqueAction.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, EvaluationListByAction.class);
            startActivity(intent);
        });
        // Élément cliquable pour Évaluation du risque par site
        TextView itemEvaluationRisqueSite = findViewById(R.id.item_evaluation_risque_site);
        itemEvaluationRisqueSite.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, EvaluationListBySite.class);
            startActivity(intent);
        });
        // Élément cliquable pour Plans d'action par Unité de travail
        TextView itemPlansAction = findViewById(R.id.item_plans_action);
        itemPlansAction.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ActionPlansByUtr.class);
            startActivity(intent);
        });

    }
}
