package com.example.one_mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class PlansActionUniteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_action_unite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Plans d'action par Unité de travail");
        }
    }
}
