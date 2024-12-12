package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set the title dynamically
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Document Unique");
        }

        // Set up click listeners
        TextView itemDangers = findViewById(R.id.item_dangers);
        TextView itemOrigineDeRisque = findViewById(R.id.item_origine_de_risque);
        TextView itemEvaluationSite = findViewById(R.id.item_evaluation_site); // Nouveau item

        itemDangers.setOnClickListener(v -> {
            // Navigate to DangersActivity
            Intent intent = new Intent(HomeActivity.this, DangersActivity.class);
            startActivity(intent);
        });

        itemOrigineDeRisque.setOnClickListener(v -> {
            // Navigate to OrigineDeRisqueActivity
            Intent intent = new Intent(HomeActivity.this, OrigineDeRisqueActivity.class);
            startActivity(intent);
        });

//        itemEvaluationSite.setOnClickListener(v -> {
//            // Navigate to EvaluationSiteFormActivity
//            Intent intent = new Intent(HomeActivity.this, EvaluationSiteFormActivity.class);
//            startActivity(intent);
//        });
    }
}
