package com.example.StepTracker;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class WeekOverviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_overview);

        // Zurück-Button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Balkendiagramm initialisieren
        BarChart barChart = findViewById(R.id.weekBarChart);

        // Beispieldaten für die Woche
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 12000)); // Montag
        entries.add(new BarEntry(1, 10599)); // Dienstag
        entries.add(new BarEntry(2, 11989)); // Mittwoch
        entries.add(new BarEntry(3, 10387)); // Donnerstag
        entries.add(new BarEntry(4, 7000));  // Freitag
        entries.add(new BarEntry(5, 3800));  // Samstag
        entries.add(new BarEntry(6, 6000));  // Sonntag

        BarDataSet dataSet = new BarDataSet(entries, "Schritte");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Achsen konfigurieren
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"MO", "DI", "MI", "DO", "FR", "SA", "SO"}));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate(); // Refresh
    }
}
