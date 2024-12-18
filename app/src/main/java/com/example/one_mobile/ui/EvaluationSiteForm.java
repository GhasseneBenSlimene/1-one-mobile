package com.example.one_mobile.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.data.model.EvaluationSite;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;

import java.util.ArrayList;
import java.util.List;

public class EvaluationSiteForm extends AppCompatActivity {

    private EvaluationSiteViewModel viewModel;

    private Spinner siteSpinner, originSpinner, matriceSpinner;
    private EditText descriptionEditText;
    private Button submitButton;
    private LinearLayout factorsContainer;

    private Site selectedSite;
    private Origine selectedOrigine;
    private Matrice selectedMatrice;
    private List<MatriceFacteur> matriceFacteurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_site_form);

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(EvaluationSiteViewModel.class);

        // Bind UI elements
        siteSpinner = findViewById(R.id.site_spinner);
        originSpinner = findViewById(R.id.origin_spinner);
        matriceSpinner = findViewById(R.id.matrice_spinner);
        descriptionEditText = findViewById(R.id.description_edit_text);
        submitButton = findViewById(R.id.submit_button);
        factorsContainer = findViewById(R.id.factors_container);

        // Load data for spinners
        loadSites();
        loadOrigins();
        loadMatrices();

        matriceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMatrice = (Matrice) matriceSpinner.getSelectedItem();
                loadFactors(selectedMatrice.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedMatrice = null;
            }
        });

        // Handle submit button click
        submitButton.setOnClickListener(v -> {
            String description = descriptionEditText.getText().toString();

            if (selectedSite == null || selectedOrigine == null || selectedMatrice == null || description.isEmpty()) {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            EvaluationSite evaluationSite = new EvaluationSite();
            evaluationSite.setSite(selectedSite);
            evaluationSite.getEvaluation().setOrigine(selectedOrigine.getLib());
            evaluationSite.getEvaluation().setMatrice(selectedMatrice);
            evaluationSite.getEvaluation().setDesc(description);

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
                ArrayAdapter<Site> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sites);
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

    private void loadOrigins() {
        viewModel.getAllOrigines().observe(this, origins -> {
            if (origins != null && !origins.isEmpty()) {
                ArrayAdapter<Origine> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, origins);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                originSpinner.setAdapter(adapter);

                originSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedOrigine = origins.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedOrigine = null;
                    }
                });
            }
        });
    }

    private void loadMatrices() {
        viewModel.getAllMatrices().observe(this, matrices -> {
            if (matrices != null && !matrices.isEmpty()) {
                ArrayAdapter<Matrice> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matrices);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                matriceSpinner.setAdapter(adapter);
            }
        });
    }

    private void loadFactors(long matriceId) {
        viewModel.getMatriceFacteursByMatriceId(matriceId).observe(this, factors -> {
            if (factors != null && !factors.isEmpty()) {
                matriceFacteurs = factors;
                factorsContainer.removeAllViews();

                for (MatriceFacteur matriceFacteur : factors) {
                    Facteur facteur = matriceFacteur.getFacteur();
                    if (facteur.getType() == 0) { // Type libre
                        EditText editText = new EditText(this);
                        editText.setHint(facteur.getLib());
                        factorsContainer.addView(editText);
                    } else if (facteur.getType() == 1) { // Type liste
                        Spinner spinner = new Spinner(this);
                        viewModel.getValeursByFacteurId(facteur.getId()).observe(this, valeurs -> {
                            if (valeurs != null && !valeurs.isEmpty()) {
                                ArrayAdapter<Valeur> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valeurs);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(adapter);
                            }
                        });
                        factorsContainer.addView(spinner);
                    }
                }
            }
        });
    }
}
