package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSiteWithDetails;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModelFactory;

import java.util.List;

public class EvaluationListBySite extends BaseActivity {

    private EvaluationSiteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_list_by_site);

        Button buttonAjouter = findViewById(R.id.button_ajouter);

        buttonAjouter.setOnClickListener(v -> openAddDialog());

        // Créer une instance de ViewModel avec le factory
        EvaluationSiteViewModelFactory factory = new EvaluationSiteViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(EvaluationSiteViewModel.class);

        // Observer les données
        viewModel.getAllEvaluationSites().observe(this, this::populateTable);
    }


    private void openAddDialog() {
        Intent intent = new Intent(EvaluationListBySite.this, EvaluationSiteForm.class);
        startActivity(intent);
    }

    private void populateTable(List<EvaluationSiteWithDetails> evaluationSites) {
        if (evaluationSites == null || evaluationSites.isEmpty()) {
            Toast.makeText(this, "Aucune évaluation trouvée", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_evaluation_risque);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EvaluationSiteAdapter adapter = new EvaluationSiteAdapter(evaluationSites, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(evaluationSite -> {
            EvaluationDetailsDialog dialog = new EvaluationDetailsDialog(evaluationSite, new EvaluationDetailsDialog.OnActionListener() {
                @Override
                public void onModify(EvaluationSiteWithDetails evaluationSite) {
                    // Logique de modification
                }

                @Override
                public void onDelete(EvaluationSiteWithDetails evaluationSite) {
                    viewModel.getAllEvaluationSites().observe(EvaluationListBySite.this, EvaluationListBySite.this::populateTable);
                }
            });
            dialog.show(getSupportFragmentManager(), "EvaluationDetailsDialog");
        });
    }

}