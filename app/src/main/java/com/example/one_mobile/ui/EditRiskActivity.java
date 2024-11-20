package com.example.one_mobile.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.one_mobile.R;
import com.example.one_mobile.data.local.RiskEvaluation;
import com.example.one_mobile.viewmodel.RiskViewModel;

public class EditRiskActivity extends AppCompatActivity {
    private RiskViewModel viewModel;
    private EditText etTitle, etDescription;
    private Button btnSave;
    private RiskEvaluation risk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_risk);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSave = findViewById(R.id.btnSave);

        viewModel = new ViewModelProvider(this).get(RiskViewModel.class);

        // Vérifie si un risque est transmis
        risk = (RiskEvaluation) getIntent().getSerializableExtra("risk");
        if (risk != null) {
            // Pré-remplit les champs pour modification
            etTitle.setText(risk.getTitle());
            etDescription.setText(risk.getDescription());
        }

        btnSave.setOnClickListener(v -> {
            if (risk == null) {
                risk = new RiskEvaluation();
            }
            risk.setTitle(etTitle.getText().toString());
            risk.setDescription(etDescription.getText().toString());

            if (risk.getId() == 0) {
                viewModel.addRisk(risk);  // Ajout
            } else {
                viewModel.updateRisk(risk);  // Mise à jour
            }
            finish();  // Retourne à MainActivity
        });
    }
}
