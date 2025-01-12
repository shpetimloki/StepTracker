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

public class MonthOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_overview);

        // Zurück-Button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Balkendiagramm initialisieren
        BarChart barChart = findViewById(R.id.monthBarChart);

        // Beispieldaten für Monate
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 12000)); // Januar
        entries.add(new BarEntry(1, 10599)); // Februar
        entries.add(new BarEntry(2, 6000));  // März
        entries.add(new BarEntry(3, 11989)); // April
        entries.add(new BarEntry(4, 10387)); // Mai
        entries.add(new BarEntry(5, 7000));  // Juni
        entries.add(new BarEntry(6, 10100)); // Juli
        entries.add(new BarEntry(7, 9450));  // August
        entries.add(new BarEntry(8, 8000));  // September
        entries.add(new BarEntry(9, 11989)); // Oktober
        entries.add(new BarEntry(10, 14000)); // November
        entries.add(new BarEntry(11, 9000));  // Dezember

        BarDataSet dataSet = new BarDataSet(entries, "Schritte");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        // Achsen konfigurieren
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez"}));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate(); // Refresh
    }
}
