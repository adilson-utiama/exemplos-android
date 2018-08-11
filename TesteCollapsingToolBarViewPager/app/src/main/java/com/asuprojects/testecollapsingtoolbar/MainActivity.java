package com.asuprojects.testecollapsingtoolbar;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuraToolBar();

        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        ArrayList<Pessoa> listaPessoas = gerarPessoas();

        adapter.setListaPessoa(listaPessoas);

        viewPager.setAdapter(adapter);





    }

    private ArrayList<Pessoa> gerarPessoas() {
        ArrayList<Pessoa> lista = new ArrayList<>();
        lista.add(new Pessoa("Adilson", "adilson@gmail.com"));
        lista.add(new Pessoa("Wilson", "wilson@email.com"));
        lista.add(new Pessoa("Manuel", "manuel.yahoo.com"));

        return lista;
    }

    private void configuraToolBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Exemplo Collapse");

        }
    }
}
