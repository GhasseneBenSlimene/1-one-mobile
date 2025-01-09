package com.example.one_mobile.ui;

import android.content.Intent;
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
import com.example.one_mobile.data.local.Dto.EvaluationDTO;
import com.example.one_mobile.data.local.Dto.EvaluationSiteWithDetailsDTO;
import com.example.one_mobile.data.model.Facteur;
import com.example.one_mobile.data.model.Matrice;
import com.example.one_mobile.data.model.MatriceFacteur;
import com.example.one_mobile.data.model.Origine;
import com.example.one_mobile.data.model.Risque;
import com.example.one_mobile.data.model.Site;
import com.example.one_mobile.data.model.Valeur;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModelFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class EvaluationSiteForm extends AppCompatActivity {

    private EvaluationSiteViewModel viewModel;

    private Spinner siteSpinner;

    private Spinner riskSpinner;
    private Spinner originSpinner;
    private Spinner matriceSpinner;
    private EditText descriptionEditText;
    private LinearLayout factorsContainer;
    private Button submitButton;

    private Site selectedSite;

    private Risque selectedRisque;
    private Origine selectedOrigine;
    private Matrice selectedMatrice;

    private final HashMap<Long, Object> factorValues = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_site_form);
        setContentView(R.layout.evaluation_site_form);

        EvaluationSiteViewModelFactory factory = new EvaluationSiteViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(EvaluationSiteViewModel.class);

        siteSpinner = findViewById(R.id.site_spinner);
        originSpinner = findViewById(R.id.origin_spinner);
        riskSpinner = findViewById(R.id.risk_spinner);
        matriceSpinner = findViewById(R.id.matrice_spinner);
        descriptionEditText = findViewById(R.id.description_edit_text);
        factorsContainer = findViewById(R.id.factors_container);
        submitButton = findViewById(R.id.submit_button);

        loadSites();
        loadRisks();
        loadOrigins();
        loadMatrices();

        submitButton.setOnClickListener(v -> submitEvaluationSite());
    }

    private void loadSites() {
        viewModel.getAllSites().observe(this, sites -> {
            if (sites != null && !sites.isEmpty()) {
                List<String> siteNames = new ArrayList<>();
                siteNames.add(""); // Add placeholder

                for (Site site : sites) {
                    siteNames.add(site.getLib() + " (ID: " + site.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, siteNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                siteSpinner.setAdapter(adapter);

                siteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) { // First item selected
                            selectedSite = null;
                        } else {
                            selectedSite = sites.get(position - 1); // Adjust index for placeholder
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedSite = null;
                    }
                });
            }
        });
    }

    private void loadRisks(){
        viewModel.getAllRisques().observe(this, risques -> {
            if (risques != null && !risques.isEmpty()) {
                List<String> risqueNames = new ArrayList<>();
                risqueNames.add(""); // Add placeholder

                for (Risque risque : risques) {
                    risqueNames.add(risque.getLib() + " (ID: " + risque.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, risqueNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                riskSpinner.setAdapter(adapter);

                riskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) { // First item selected
                            selectedRisque = null;
                        } else {
                            selectedRisque = risques.get(position - 1); // Adjust index for placeholder
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        selectedRisque = null;
                    }
                });
            }
        });
    }

    private void loadOrigins() {
        viewModel.getAllOrigines().observe(this, origins -> {
            if (origins != null && !origins.isEmpty()) {
                List<String> originNames = new ArrayList<>();
                originNames.add(""); // Add placeholder
                for (Origine origin : origins) {
                    originNames.add(origin.getLib() + " (ID: " + origin.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, originNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                originSpinner.setAdapter(adapter);

                originSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) { // First item selected
                            selectedOrigine = null;
                        } else {
                            selectedOrigine = origins.get(position - 1); // Adjust index for placeholder
                        }
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
                matriceNames.add(""); // Add placeholder
                for (Matrice matrice : matrices) {
                    matriceNames.add(matrice.getRegle() + " (ID: " + matrice.getId() + ")");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matriceNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                matriceSpinner.setAdapter(adapter);

                matriceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) { // First item selected
                            selectedMatrice = null;
                            factorsContainer.removeAllViews();
                        } else {
                            selectedMatrice = matrices.get(position - 1); // Adjust index for placeholder
                            loadFactorsForSelectedMatrix();
                        }
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

        // Create EvaluationSite
        EvaluationSiteWithDetailsDTO evaluationSite = new EvaluationSiteWithDetailsDTO();

        // Ensure that the evaluation object is initialized
        if (evaluationSite.getEvaluation() == null) {
            evaluationSite.setEvaluation(new EvaluationDTO()); // Manually initialize if it's null
        }

        // Populate the Evaluation object inside EvaluationSite
        EvaluationDTO evaluation = evaluationSite.getEvaluation();
        evaluation.setRisque(selectedRisque);
        evaluation.setOrigine(selectedOrigine);
        evaluation.setMatrice(selectedMatrice);
        evaluation.setDesc(description);
        // evaluation.setDescCourt(description);

        // Calculate the product of factor values
        float product = calculateFactorValuesProduct();
        evaluation.setIndiceInt((int) product);
        evaluation.setIndice(product);

        // Format the date to ISO string with Locale.US
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        String formattedDate = sdf.format(currentDate);
        evaluation.setDate(formattedDate);

        // Set the valid date to one year after the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 1);
        String validDate = sdf.format(calendar.getTime());
        evaluation.setValid(validDate);


        // Set the EvaluationSite properties
        evaluationSite.setSite(selectedSite);
        evaluationSite.setEvaluation(evaluation);

        viewModel.createEvaluationSite(evaluationSite).observe(this, createdEvaluationSite -> {
            if (createdEvaluationSite != null) {
                Toast.makeText(this, "EvaluationSite created successfully!", Toast.LENGTH_SHORT).show();
                // Redirect to EvaluationListBySite activity
                Intent intent = new Intent(EvaluationSiteForm.this, EvaluationListBySite.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to create EvaluationSite", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private float calculateFactorValuesProduct() {
        float product = 1.0f;
        for (Long key : factorValues.keySet()) {
            Object value = factorValues.get(key);
            if (value instanceof EditText) {
                String text = ((EditText) value).getText().toString();
                if (!text.isEmpty()) {
                    product *= Float.parseFloat(text);
                }
            } else if (value instanceof Spinner) {
                String selectedItem = (String) ((Spinner) value).getSelectedItem();
                if (selectedItem != null && !selectedItem.isEmpty()) {
                    String[] parts = selectedItem.split(" ");
                    product *= Float.parseFloat(parts[parts.length - 1].replace("(", "").replace(")", ""));
                }
            }
        }
        return product;
    }
}
