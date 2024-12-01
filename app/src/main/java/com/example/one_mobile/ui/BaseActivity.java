package com.example.one_mobile.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.one_mobile.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        // Charge le layout de la toolbar
        super.setContentView(layoutResID);
        getLayoutInflater().inflate(R.layout.toolbar, findViewById(android.R.id.content), true);

        // Configurez les éléments de la toolbar
        setupToolbar();
    }

    private void setupToolbar() {
        SharedPreferences preferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "Utilisateur");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText("Bonjour, " + username);

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            // Efface les préférences utilisateur et retourne à l'écran d'authentification
            preferences.edit().clear().apply();
            Intent intent = new Intent(this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
