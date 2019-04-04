package com.asuprojects.testescomponentes.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.asuprojects.testescomponentes.R;

public class ResultAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_alarm);

        Toast.makeText(this, "Alarme Acionado!", Toast.LENGTH_SHORT).show();
    }
}
