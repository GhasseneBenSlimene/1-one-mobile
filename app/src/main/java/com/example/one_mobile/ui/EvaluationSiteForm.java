package com.example.one_mobile.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;

public class EvaluationSiteForm extends AppCompatActivity {

    private EvaluationSiteViewModel viewModel;

    private Spinner siteSpinner;
    private Spinner originSpinner;
    private Spinner matriceSpinner;
    private EditText descriptionEditText;
    private LinearLayout factorsContainer;
    private Button submitButton;

    private Site selectedSite;
    private Origine selectedOrigine;
    private Matrice selectedMatrice;

    private final HashMap<Long, Object> factorValues = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_site_form);

        viewModel = new ViewModelProvider(this).get(EvaluationSiteViewModel.class);

        siteSpinner = findViewById(R.id.site_spinner);
        originSpinner = findViewById(R.id.origin_spinner);
        matriceSpinner = findViewById(R.id.matrice_spinner);
        descriptionEditText = findViewById(R.id.description_edit_text);
        factorsContainer = findViewById(R.id.factors_container);
        submitButton = findViewById(R.id.submit_button);

        loadSites();
        loadOrigins();
        loadMatrices();

        submitButton.setOnClickListener(v -> submitEvaluationSite());
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

    private void loadOrigins() {
        viewModel.getAllOrigines().observe(this, origins -> {
            if (origins != null && !origins.isEmpty()) {
                List<String> originNames = new ArrayList<>();
                for (Origine origin : origins) {
                    originNames.add(origin.getLib() + " (ID: " + origin.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, originNames);
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
                        loadFactorsForSelectedMatrix();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedMatrice = null;
                        factorsContainer.removeAllViews();
                    }
                });
            }
        });
    }

    private void loadFactorsForSelectedMatrix() {
        if (selectedMatrice == null) {
            return;
        }

        viewModel.getMatriceFacteursByMatriceId(selectedMatrice.getId()).observe(this, matriceFacteurs -> {
            if (matriceFacteurs != null && !matriceFacteurs.isEmpty()) {
                factorsContainer.removeAllViews();

                for (MatriceFacteur matriceFacteur : matriceFacteurs) {
                    Facteur facteur = matriceFacteur.getFacteur();

                    if (facteur.getType() == 0) { // Type Libre
                        EditText editText = new EditText(this);
                        editText.setHint(facteur.getLib());
                        editText.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));
                        factorsContainer.addView(editText);
                        factorValues.put(facteur.getId(), editText);

                    } else if (facteur.getType() == 1) { // Type Liste
                        Spinner spinner = new Spinner(this);
                        spinner.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));

                        viewModel.getValeursByFacteurId(facteur.getId()).observe(this, valeurs -> {
                            if (valeurs != null && !valeurs.isEmpty()) {
                                List<String> valeurNames = new ArrayList<>();
                                for (Valeur valeur : valeurs) {
                                    valeurNames.add(valeur.getLib() + " (" + valeur.getValue() + ")");
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valeurNames);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(adapter);
                            }
                        });

                        factorsContainer.addView(spinner);
                        factorValues.put(facteur.getId(), spinner);
                    }
                }
            }
        });
    }

    private void submitEvaluationSite() {
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

//        viewModel.createEvaluationSite(evaluationSite).observe(this, createdSite -> {
//            if (createdSite != null) {
//                Toast.makeText(this, "EvaluationSite created successfully!", Toast.LENGTH_SHORT).show();
//                finish();
//            } else {
//                Toast.makeText(this, "Failed to create EvaluationSite", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
