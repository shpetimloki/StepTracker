package com.example.StepTracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // SharedPreferences zum Speichern des Login-Status
        SharedPreferences sharedPreferences = getSharedPreferences("StepTrackerPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        // Prüfe Login-Status
        if (isLoggedIn) {
            // Benutzer ist angemeldet -> Weiterleitung zur Schrittzähler-Seite
            Intent intent = new Intent(LoginSelectionActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Zeige Login- oder Registrierungsseite
        setContentView(R.layout.activity_login_selection);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginSelectionActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginSelectionActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
