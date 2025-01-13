package com.example.StepTracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    public int stepCount = 0;
    public int dailyGoal = 10000; // Standardziel
    private TextView goalTextView;
    private Button changeGoalButton, weekOverviewButton, monthOverviewButton, logoutButton;

    public String getUpdatedGoalText() {
        return stepCount + " von " + dailyGoal + " Schritten erreicht";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Überprüfen, ob der Benutzer angemeldet ist
        SharedPreferences sharedPreferences = getSharedPreferences("StepTrackerPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            // Wenn nicht eingeloggt, zurück zur Login-Seite
            Intent intent = new Intent(MainActivity.this, LoginSelectionActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // Views initialisieren
        goalTextView = findViewById(R.id.goalTextView);
        changeGoalButton = findViewById(R.id.changeGoalButton);
        weekOverviewButton = findViewById(R.id.weekOverviewButton);
        monthOverviewButton = findViewById(R.id.monthOverviewButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Schrittzähler einrichten
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }

        // Ziel laden
        dailyGoal = sharedPreferences.getInt("dailyGoal", 10000); // Lade gespeichertes Ziel
        updateGoalText();

        // Ziel ändern
        changeGoalButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SetGoalActivity.class);
            startActivity(intent);
        });

        // Wochenübersicht
        weekOverviewButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WeekOverviewActivity.class);
            startActivity(intent);
        });

        // Monatsübersicht
        monthOverviewButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MonthOverviewActivity.class);
            startActivity(intent);
        });

        // Abmelde-Logik
        logoutButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Abmelden")
                    .setMessage("Möchtest du dich wirklich abmelden?")
                    .setPositiveButton("Ja", (dialog, which) -> {
                        // Login-Status zurücksetzen
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", false); // Status auf "nicht eingeloggt" setzen
                        editor.apply();

                        // Zurück zur Login-Seite
                        Intent intent = new Intent(MainActivity.this, LoginSelectionActivity.class);
                        startActivity(intent);
                        finish(); // MainActivity schließen
                    })
                    .setNegativeButton("Nein", null) // Dialog schließen, wenn "Nein" gedrückt wird
                    .show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Schrittzähler-Sensor registrieren
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        // Neues Ziel aus SharedPreferences laden und anzeigen
        SharedPreferences sharedPreferences = getSharedPreferences("StepTrackerPrefs", MODE_PRIVATE);
        dailyGoal = sharedPreferences.getInt("dailyGoal", 10000); // Standardziel
        updateGoalText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCount = (int) event.values[0];
            updateGoalText();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Nicht benötigt
    }

    private void updateGoalText() {
        goalTextView.setText(stepCount + " von " + dailyGoal + " Schritten erreicht");
    }


}
