package com.asuprojects.testecollapsingtoolbar;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.asuprojects.testecollapsingtoolbar.util.GeradorPessoas;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Pessoa> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuraToolbar();

        lista = GeradorPessoas.gerarLista(10);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(lista);
        adapter.setOnClickListener(new PessoaOnClickListener() {
            @Override
            public void onClick(int posicao) {

                Pessoa pessoa = lista.get(posicao);
                Intent intent = new Intent(MainActivity.this, PessoaActivity.class);
                intent.putExtra("PESSOA", pessoa);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void configuraToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Exemplo Collapse");
        }
    }
}
