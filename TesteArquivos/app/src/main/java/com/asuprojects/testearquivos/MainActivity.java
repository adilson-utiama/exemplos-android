package com.asuprojects.testearquivos;

import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton btnDiretorios;
    private AppCompatButton btnWrite;
    private AppCompatButton btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDiretorios = findViewById(R.id.btn_diretorios);
        btnWrite = findViewById(R.id.btn_write);
        btnRead = findViewById(R.id.btn_read);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frameLayout, new DiretoriosFragment());
        tx.commit();

        btnDiretorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.frameLayout, new DiretoriosFragment());
                tx.commit();
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.frameLayout, new WriteFragment());
                tx.commit();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.frameLayout, new ReadFragment());
                tx.commit();
            }
        });

    }
}
