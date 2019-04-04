package com.asuprojects.testescomponentes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WidgetsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Widgets Exexmplos");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
