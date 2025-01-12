package com.example.StepTracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetGoalActivity extends AppCompatActivity {

    private EditText currentGoalEditText, newGoalEditText;
    private Button saveGoalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        // Views initialisieren
        currentGoalEditText = findViewById(R.id.currentGoalEditText);
        newGoalEditText = findViewById(R.id.newGoalEditText);
        saveGoalButton = findViewById(R.id.saveGoalButton);

        // Aktuelles Ziel laden
        SharedPreferences sharedPreferences = getSharedPreferences("StepTrackerPrefs", MODE_PRIVATE);
        int currentGoal = sharedPreferences.getInt("dailyGoal", 10000); // Standardziel
        currentGoalEditText.setText(String.valueOf(currentGoal));

        // Ziel speichern
        saveGoalButton.setOnClickListener(v -> {
            String newGoalStr = newGoalEditText.getText().toString();
            if (!newGoalStr.isEmpty()) {
                int newGoal = Integer.parseInt(newGoalStr);

                // Ziel in SharedPreferences speichern
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("dailyGoal", newGoal);
                editor.apply();

                Toast.makeText(SetGoalActivity.this, "Neues Ziel gespeichert!", Toast.LENGTH_SHORT).show();

                // Zurück zur MainActivity
                finish();
            } else {
                Toast.makeText(SetGoalActivity.this, "Bitte ein gültiges Ziel eingeben!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
