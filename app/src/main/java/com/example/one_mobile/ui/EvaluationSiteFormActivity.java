package com.example.one_mobile.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;

import java.util.ArrayList;
import java.util.List;

public class EvaluationSiteFormActivity extends AppCompatActivity {

    private EvaluationSiteViewModel viewModel;

    private Spinner siteSpinner;
    private Spinner matriceSpinner;
    private EditText origineEditText;
    private EditText indiceEditText;
    private EditText indiceIntEditText;
    private EditText descriptionEditText;
    private Button submitButton;

    private Site selectedSite;
    private Matrice selectedMatrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_site_form);

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(EvaluationSiteViewModel.class);

        // Bind UI elements
        siteSpinner = findViewById(R.id.site_spinner);
        matriceSpinner = findViewById(R.id.matrice_spinner);
        origineEditText = findViewById(R.id.origine_edit_text);
        indiceEditText = findViewById(R.id.indice_edit_text);
        indiceIntEditText = findViewById(R.id.indice_int_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        submitButton = findViewById(R.id.submit_button);

        loadSites();
        loadMatrices();

        // Handle submit button click
        submitButton.setOnClickListener(v -> {
            String origine = origineEditText.getText().toString();
            String indice = indiceEditText.getText().toString();
            String indiceInt = indiceIntEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (selectedSite == null || selectedMatrice == null || origine.isEmpty() || indice.isEmpty() || indiceInt.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            EvaluationSite evaluationSite = new EvaluationSite();
            evaluationSite.setSite(selectedSite);
            evaluationSite.setMatrice(selectedMatrice);
            evaluationSite.setOrigine(origine);
            evaluationSite.setIndice(Double.parseDouble(indice));
            evaluationSite.setIndiceInt(Integer.parseInt(indiceInt));
            evaluationSite.setDescription(description);

            viewModel.createEvaluationSite(evaluationSite);
            viewModel.getCreatedEvaluationSite().observe(this, createdSite -> {
                if (createdSite != null) {
                    Toast.makeText(this, "EvaluationSite created successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to create EvaluationSite", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void loadSites() {
        viewModel.getAllSites().observe(this, sites -> {
            if (sites != null && !sites.isEmpty()) {
                List<String> siteNames = new ArrayList<>();
                for (Site site : sites) {
                    siteNames.add(site.getLib() + " (ID: " + site.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, siteNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                siteSpinner.setAdapter(adapter);

                siteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedSite = sites.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedSite = null;
                    }
                });
            }
        });
    }

    private void loadMatrices() {
        viewModel.getAllMatrices().observe(this, matrices -> {
            if (matrices != null && !matrices.isEmpty()) {
                List<String> matriceNames = new ArrayList<>();
                for (Matrice matrice : matrices) {
                    matriceNames.add(matrice.getRegle() + " (ID: " + matrice.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matriceNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                matriceSpinner.setAdapter(adapter);

                matriceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedMatrice = matrices.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedMatrice = null;
                    }
                });
            }
        });
    }
}
