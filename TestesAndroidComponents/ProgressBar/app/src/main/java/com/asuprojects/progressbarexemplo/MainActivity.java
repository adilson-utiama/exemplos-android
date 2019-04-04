package com.asuprojects.progressbarexemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar, btnReset, btnStep;
    private ProgressBar progressHorizontal, progressCircular;

    private int progresso = 0;
    private int maximo = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        progressHorizontal.setMax(maximo);
        progressCircular.setMax(maximo);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            progressCircular.setVisibility(View.VISIBLE);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    for(int i = 0; i < 5; i++){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progresso += 20;
                                progressHorizontal.setProgress(progresso);
                                progressCircular.setProgress(progresso);
                                if(progresso == maximo) {
                                    progressCircular.setVisibility(View.GONE);
                                    progresso = 0;
                                    progressHorizontal.setProgress(progresso);
                                    progressCircular.setProgress(progresso);
                                }
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progresso = 0;
                progressHorizontal.setProgress(progresso);
                progressCircular.setProgress(progresso);
                progressCircular.setVisibility(View.GONE);
            }
        });

        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressCircular.setVisibility(View.VISIBLE);
                if(progresso == maximo) {
                    progressCircular.setVisibility(View.GONE);
                } else {
                    progresso += 10;
                    progressHorizontal.setProgress(progresso);
                    progressCircular.setProgress(progresso);
                }

            }
        });


    }

    private void inicializarComponentes() {
        progressHorizontal = findViewById(R.id.progressBarHorizontal);
        progressCircular = findViewById(R.id.progressBar);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnReset = findViewById(R.id.btnReset);
        btnStep = findViewById(R.id.btnStep);
    }
}
