package com.asuprojects.testehellocharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MPAndroidChartActivity extends AppCompatActivity
        implements OnChartValueSelectedListener {

    private PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);

        chart = findViewById(R.id.mp_chart);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(15L, "Compras"));
        entries.add(new PieEntry(85L, "Contas"));
        entries.add(new PieEntry(45L, "Lanches"));
        entries.add(new PieEntry(37L, "Utilitarios"));
        entries.add(new PieEntry(25L, "Transporte"));
        entries.add(new PieEntry(65L, "Alimentação"));

        PieDataSet dataSet = new PieDataSet(entries, "Despesas");
        dataSet.setColors(gerarCores());

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.setOnChartValueSelectedListener(this);
        chart.setDrawHoleEnabled(false);
        chart.setUsePercentValues(true);
        chart.setCenterTextColor(Color.WHITE);
//        chart.setCenterText("Despesas");
//        chart.setCenterTextColor(android.R.color.white);
//        chart.setHoleColor(R.color.backgroud);
        chart.setEntryLabelColor(Color.rgb(89,89,89));
        //chart.setHoleRadius(0);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "R$ " + e.getY(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    private List<Integer> gerarCores(){
        List<Integer> cores = new ArrayList<>();
//        cores.add(Color.BLUE);
//        cores.add(Color.GREEN);
//        cores.add(Color.MAGENTA);
        int azul = Color.rgb(95,170,255);
        int verde = Color.rgb(92, 196, 114);
        int caqui = Color.rgb(185, 196, 92);
        int laranja = Color.rgb(240, 195, 90);
        int vermelho = Color.rgb(235, 70, 70);
        int roxo = Color.rgb(215, 150, 250);
        int rosa = Color.rgb(250, 150, 215);
        int amarelo = Color.rgb(230, 220, 50);
        cores.addAll(Arrays.asList(azul, verde, laranja, vermelho, roxo, rosa, amarelo, caqui));
        return cores;
    }
}
