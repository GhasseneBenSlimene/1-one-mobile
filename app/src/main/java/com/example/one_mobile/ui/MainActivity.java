package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.one_mobile.R;
import com.example.one_mobile.data.local.RiskEvaluation;
import com.example.one_mobile.viewmodel.RiskViewModel;

public class MainActivity extends AppCompatActivity {
    private RiskViewModel viewModel;
    private RiskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(RiskViewModel.class);

        adapter = new RiskAdapter(this, new RiskAdapter.OnRiskActionListener() {
            @Override
            public void onEdit(RiskEvaluation risk) {
                Intent intent = new Intent(MainActivity.this, EditRiskActivity.class);
                intent.putExtra("risk", risk);  // Transmettre l'objet RiskEvaluation
                startActivity(intent);
            }

            @Override
            public void onDelete(RiskEvaluation risk) {
                viewModel.deleteRisk(risk);  // Supprimer via le ViewModel
                Toast.makeText(MainActivity.this, "Risque supprimé", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

        viewModel.getAllRisks().observe(this, risks -> {
            if (risks != null) {
                adapter.submitList(risks);
            }
        });


        // Ajouter un risque
        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditRiskActivity.class);
            startActivity(intent);  // Ouvre l'interface pour ajouter un risque
        });

    }
}
