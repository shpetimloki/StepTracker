package com.example.StepTracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, emailEditText;
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        registerButton = findViewById(R.id.registerButton);

        dbHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Alle Felder ausfüllen!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isRegistered = dbHelper.registerUser(username, password, email);
            if (isRegistered) {
                Toast.makeText(this, "Registrierung erfolgreich!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Fehler bei der Registrierung!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
