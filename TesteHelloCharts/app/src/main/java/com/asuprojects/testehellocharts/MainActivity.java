package com.asuprojects.testehellocharts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnHelloChart;
    private Button btnMpAndroidChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHelloChart = findViewById(R.id.btn_hellochart);
        btnHelloChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HelloChartActivity.class));
            }
        });

        btnMpAndroidChart = findViewById(R.id.btn_mpchart);
        btnMpAndroidChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MPAndroidChartActivity.class));
            }
        });

    }


}
