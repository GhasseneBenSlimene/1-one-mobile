package com.example.one_mobile.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class OrigineDeRisqueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origine_de_risque);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Origine de risque");
        }
    }
}
