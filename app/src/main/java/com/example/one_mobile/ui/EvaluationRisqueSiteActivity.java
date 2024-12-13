package com.example.one_mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class EvaluationRisqueSiteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_risque_site);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Évaluation du risque par site");
        }
    }
}
