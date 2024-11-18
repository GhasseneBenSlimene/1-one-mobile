package com.example.one_mobile.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_mobile.R;
import com.example.one_mobile.data.local.RiskEvaluation;
import com.example.one_mobile.viewmodel.RiskViewModel;

public class MainActivity extends AppCompatActivity {

    private RiskViewModel riskViewModel;
    private RiskAdapter riskAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        riskAdapter = new RiskAdapter();
        recyclerView.setAdapter(riskAdapter);

        progressBar = findViewById(R.id.progress_bar);

        riskViewModel = new ViewModelProvider(this).get(RiskViewModel.class);

        // Observer les risques
        riskViewModel.getAllRisks().observe(this, risks -> riskAdapter.setRisks(risks));

        // Observer l’état de chargement
        riskViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(android.view.View.VISIBLE);
            } else {
                progressBar.setVisibility(android.view.View.GONE);
            }
        });

        // Boutons pour tester les fonctionnalités
        Button btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            RiskEvaluation risk = new RiskEvaluation();
            risk.setTitle("Nouveau Risque");
            risk.setDescription("Description du risque");
            riskViewModel.addRisk(risk);
        });

        Button btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(v -> {
            RiskEvaluation risk = new RiskEvaluation();
            risk.setId(1); // Exemple : mettre à jour l’élément avec ID 1
            risk.setTitle("Risque Mis à Jour");
            risk.setDescription("Description mise à jour");
            riskViewModel.updateRisk(risk);
        });

        Button btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(v -> {
            RiskEvaluation risk = new RiskEvaluation();
            risk.setId(1); // Exemple : supprimer l’élément avec ID 1
            riskViewModel.deleteRisk(risk);
        });

        Button btnSync = findViewById(R.id.btn_sync);
        btnSync.setOnClickListener(v -> riskViewModel.synchronize("2024-01-01T00:00:00Z")); // Exemple de timestamp
    }
}
