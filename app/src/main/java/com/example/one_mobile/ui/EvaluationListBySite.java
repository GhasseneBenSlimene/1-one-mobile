package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;

import java.util.List;

public class EvaluationListBySite extends AppCompatActivity {

    private TableLayout tableLayout;
    private EvaluationSiteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_list_by_site);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Évaluation du risque par Site");
        }

        tableLayout = findViewById(R.id.table_evaluation_risque);
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

        for (EvaluationSite evaluationSite : evaluationSites) {
            TableRow row = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(evaluationSite.getId()));
            row.addView(idTextView);

            TextView siteTextView = new TextView(this);
            siteTextView.setText(evaluationSite.getSite().getLib());
            row.addView(siteTextView);

            TextView risqueTextView = new TextView(this);
            risqueTextView.setText(evaluationSite.getEvaluation().getRisque().getLib());
            row.addView(risqueTextView);

            TextView descTextView = new TextView(this);
            descTextView.setText(evaluationSite.getEvaluation().getDesc());
            row.addView(descTextView);

            tableLayout.addView(row);
        }
    }
}
