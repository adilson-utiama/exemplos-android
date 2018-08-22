package com.asuprojects.testefragment.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.asuprojects.testefragment.R;
import com.asuprojects.testefragment.fragments.PrimeiroFragment;
import com.asuprojects.testefragment.fragments.SegundoFragment;
import com.asuprojects.testefragment.fragments.TerceiroFragment;

public class MainActivity extends AppCompatActivity {

    private Button btnPrimeiro, btnSegundo, btnTerceiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrimeiro = findViewById(R.id.btnPrimeiro);
        btnSegundo = findViewById(R.id.btnSegundo);
        btnTerceiro = findViewById(R.id.btnTerceiro);



        PrimeiroFragment primeiro = new PrimeiroFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, primeiro);
        transaction.commit();

        btnPrimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrimeiroFragment primeiro = new PrimeiroFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_lin_left, R.anim.slide_out_right);
                transaction.replace(R.id.frameConteudo, primeiro);
                transaction.commit();
            }
        });

        btnSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SegundoFragment segundo = new SegundoFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_lin_left, R.anim.slide_out_right);
                transaction.replace(R.id.frameConteudo, segundo);
                transaction.commit();
            }
        });

        btnTerceiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TerceiroFragment terceiro = new TerceiroFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_lin_left, R.anim.slide_out_right);
                transaction.replace(R.id.frameConteudo, terceiro);
                transaction.commit();
            }
        });
    }
}
