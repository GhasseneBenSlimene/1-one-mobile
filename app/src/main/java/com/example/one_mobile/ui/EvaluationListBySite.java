package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;

import java.util.List;

public class EvaluationListBySite extends AppCompatActivity {

    private EvaluationSiteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_list_by_site);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Évaluation du risque par Site");
        }

        Button buttonAjouter = findViewById(R.id.button_ajouter);

        buttonAjouter.setOnClickListener(v -> openAddDialog());

        viewModel = new ViewModelProvider(this).get(EvaluationSiteViewModel.class);
        viewModel.getAllEvaluationSites().observe(this, this::populateTable);
    }

    private void openAddDialog() {
        Intent intent = new Intent(EvaluationListBySite.this, EvaluationSiteForm.class);
        startActivity(intent);
    }

    private void populateTable(List<EvaluationSite> evaluationSites) {
        if (evaluationSites == null || evaluationSites.isEmpty()) {
            Toast.makeText(this, "Aucune évaluation trouvée", Toast.LENGTH_SHORT).show();
            return;
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_evaluation_risque);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EvaluationSiteAdapter adapter = new EvaluationSiteAdapter(evaluationSites);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(evaluationSite -> {
            EvaluationDetailsDialog dialog = new EvaluationDetailsDialog(evaluationSite, new EvaluationDetailsDialog.OnActionListener() {
                @Override
                public void onModify(EvaluationSite evaluationSite) {
                    // Logique de modification
                }

                @Override
                public void onDelete(EvaluationSite evaluationSite) {
                    // Logique de suppression
                }
            });
            dialog.show(getSupportFragmentManager(), "EvaluationDetailsDialog");
        });
    }

}
