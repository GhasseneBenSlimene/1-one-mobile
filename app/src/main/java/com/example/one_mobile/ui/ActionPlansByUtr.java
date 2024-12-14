package com.example.one_mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class ActionPlansByUtr extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_plans_by_utr);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Plans d'action par Unité de travail");
        }
    }
}
