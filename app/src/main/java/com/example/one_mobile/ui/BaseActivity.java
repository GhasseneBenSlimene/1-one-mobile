package com.example.one_mobile.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.ui.auth.AuthActivity;
import com.example.one_mobile.viewmodel.AuthViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModel;
import com.example.one_mobile.viewmodel.EvaluationSiteViewModelFactory;

public class BaseActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        preferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        setContentView(R.layout.base_activity);
        setupToolbar();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getLayoutInflater().inflate(R.layout.base_activity, findViewById(android.R.id.content), true);
        setupToolbar();
    }

    private void setupToolbar() {
        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> navigateToHome());

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> handleLogout());

        Button syncButton = findViewById(R.id.syncButton);
        syncButton.setOnClickListener(v -> syncData());

//        Button clearDatabaseButton = findViewById(R.id.clearDatabaseButton);
//        clearDatabaseButton.setOnClickListener(v -> clearDatabase());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigateToHome();
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void handleLogout() {
        authViewModel.logout().observe(this, success -> {
            if (success) {
                preferences.edit().clear().apply();
                navigateToAuthPage();
            } else {
                Toast.makeText(this, "Failed to logout", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToAuthPage() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

    private void clearDatabase() {
        EvaluationSiteViewModelFactory factory = new EvaluationSiteViewModelFactory(this);
        EvaluationSiteViewModel viewModel = new ViewModelProvider(this, factory).get(EvaluationSiteViewModel.class);

        new Thread(() -> {
            boolean isCleared = viewModel.clearDatabaseSync();
            runOnUiThread(() -> {
                if (isCleared) {
                    Toast.makeText(this, "Database cleared successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to clear database", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
