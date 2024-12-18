package com.example.one_mobile.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class EvaluationUtrForm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_utr_form);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ajouter une évaluation (Unité de travail)");
        }
    }
}


//package com.example.one_mobile.ui;
//
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.one_mobile.data.network.ApiService;
//import com.example.one_mobile.data.network.RetrofitClient;
//import com.example.one_mobile.model.Situation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class EvaluationUtrForm extends AppCompatActivity {
//    private Spinner situationSpinner;
//    private LinearLayout informationLayout;
//    private TextView codeText, risqueText, sousDomaineText, libelleText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.evaluation_utr_form); // Updated layout file name
//
//        // Initialisation des vues
//        situationSpinner = findViewById(R.id.situation_spinner);
//        informationLayout = findViewById(R.id.information_layout);
//        codeText = findViewById(R.id.code_text);
//        risqueText = findViewById(R.id.risque_text);
//        sousDomaineText = findViewById(R.id.sous_domaine_text);
//        libelleText = findViewById(R.id.libelle_text);
//
//        // Appeler l'API pour remplir le Spinner
//        fetchSituations();
//    }
//
//    private void fetchSituations() {
//        ApiService apiService = RetrofitClient.getApiService();
//        apiService.getSituations().enqueue(new Callback<List<Situation>>() {
//            @Override
//            public void onResponse(Call<List<Situation>> call, Response<List<Situation>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<Situation> situations = response.body();
//                    List<String> situationNames = new ArrayList<>();
//                    for (Situation situation : situations) {
//                        situationNames.add(situation.getName());
//                    }
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                            EvaluationUtrForm.this,
//                            android.R.layout.simple_spinner_item,
//                            situationNames
//                    );
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    situationSpinner.setAdapter(adapter);
//
//                    // Gérer la sélection
//                    situationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            Situation selectedSituation = situations.get(position);
//                            updateInformation(selectedSituation);
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {}
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Situation>> call, Throwable t) {
//                Toast.makeText(EvaluationUtrForm.this, "Erreur lors du chargement des données", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void updateInformation(Situation situation) {
//        codeText.setText("Code : " + situation.getCode());
//        risqueText.setText("Risque : " + situation.getRisque());
//        sousDomaineText.setText("Sous-domaine : " + situation.getSousDomaine());
//        libelleText.setText("Libellé : " + situation.getLibelle());
//
//        // Rendre la section visible
//        informationLayout.setVisibility(View.VISIBLE);
//    }
//}
