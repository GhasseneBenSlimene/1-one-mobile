package com.example.one_mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class EvaluationListByPost extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_list_by_post);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Évaluation du risque par poste");
        }
    }
}
