package com.example.one_mobile.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.one_mobile.R;
import com.example.one_mobile.viewmodel.AuthViewModel;

public class AuthActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth); // Met à jour pour utiliser le nouveau fichier XML

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        EditText editTextUsername = findViewById(R.id.editText_username);
        EditText editTextPassword = findViewById(R.id.editText_password);
        Button buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Étape 1 : Obtenir le token
            authViewModel.getSessionToken().observe(this, success -> {
                if (success) {
                    // Étape 2 : Authentification
                    authViewModel.authenticate(username, password).observe(this, authResponse -> {
                        if (authResponse != null) {
                            // Enregistrez le username dans SharedPreferences
                            SharedPreferences preferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
                            preferences.edit().putString("username", authResponse.getUsername()).apply();

                            // Redirigez vers l'accueil
                            Intent intent = new Intent(AuthActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Nom d'utilisateur ou mot de passe invalide", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Impossible de se connecter au serveur", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
