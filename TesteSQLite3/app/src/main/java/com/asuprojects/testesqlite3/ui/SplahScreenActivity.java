package com.asuprojects.testesqlite3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.asuprojects.testesqlite3.R;

public class SplahScreenActivity extends AppCompatActivity {

    private boolean primeiroUso = true;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);



        preferences = getSharedPreferences(getString(R.string.financas_preferences), Context.MODE_PRIVATE);
        primeiroUso =  preferences.getBoolean(getString(R.string.primeiro_uso), true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(primeiroUso){
                    Intent intent = new Intent(SplahScreenActivity.this, FirstTimeActivity.class);
                    startActivity(intent);
                }else{
                    //TODO verifica se a preferencia "manter conectado" esta true, caso sim, nao exibir tela de login

                    Intent intent = new Intent(SplahScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        }, 2500);
    }
}
