package com.example.one_mobile.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModelFactory;
import com.example.one_mobile.viewmodel.AuthViewModel;

public class BaseActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getLayoutInflater().inflate(R.layout.toolbar, findViewById(android.R.id.content), true);
        setupToolbar();
    }

    private void setupToolbar() {
        SharedPreferences preferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String username = preferences.getString("username", "Utilisateur");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText("Bonjour, " + username);

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            authViewModel.logout().observe(this, success -> {
                if (success) {
                    preferences.edit().clear().apply();
                    Intent intent = new Intent(this, AuthActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to logout", Toast.LENGTH_SHORT).show();
                }
            });
        });

        Button syncButton = findViewById(R.id.syncButton);
        syncButton.setOnClickListener(v -> syncData());
    }

    @Override
    public void onBackPressed() {
        // Navigate to the home screen instead of going back to AuthActivity
        super.onBackPressed();
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    
    private void syncData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Synchronisation en cours...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        EvaluationSiteViewModelFactory factory = new EvaluationSiteViewModelFactory(this);
        EvaluationSiteViewModel viewModel = new ViewModelProvider(this, factory).get(EvaluationSiteViewModel.class);

        viewModel.synchronizeData().observe(this, isSuccess -> {
            progressDialog.dismiss();
            if (isSuccess) {
                Toast.makeText(this, "Synchronisation réussie", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Échec de la synchronisation", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
