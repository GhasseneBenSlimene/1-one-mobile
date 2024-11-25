package com.example.one_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.one_mobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set the app's title dynamically
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mobile 1-One");
        }

        // Reference the UI elements
        EditText editTextEmail = findViewById(R.id.editText_email); // Updated ID for email field
        EditText editTextPassword = findViewById(R.id.editText_password);
        Button buttonLogin = findViewById(R.id.button_login);
        TextView forgotPassword = findViewById(R.id.textView_forgot_password);

        // Handle login button click
        if (buttonLogin != null) {
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Check if the fields are filled and valid
                    String email = editTextEmail.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    if (!isValidEmail(email)) {
                        editTextEmail.setError("Please enter a valid email");
                        return;
                    }

                    if (!isValidPassword(password)) {
                        editTextPassword.setError("Password must be at least 8 characters long and include a mix of letters, numbers, and symbols");
                        return;
                    }

                    // Prepare for backend integration (dummy code)
                    if (validateWithBackend(email, password)) {
                        // Navigate to HomeActivity
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        // Show an error message (for now, this is hardcoded)
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // Handle "Forgot Password" click
        if (forgotPassword != null) {
            forgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show a message for now
                    Toast.makeText(MainActivity.this, "Forgot Password clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Adjust padding for system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Regex for email validation
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Regex for password validation
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&      // At least one uppercase letter
                password.matches(".*[a-z].*") &&      // At least one lowercase letter
                password.matches(".*\\d.*") &&        // At least one digit
                password.matches(".*[@#$%^&+=!?].*"); // At least one special character
    }

    // Placeholder for backend validation logic
    private boolean validateWithBackend(String email, String password) {
        // Dummy implementation for now
        return email.equals("test@example.com") && password.equals("Password123!");
    }
}
