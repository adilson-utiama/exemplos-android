package com.asuprojects.testecollapsingtoolbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.Serializable;

public class PessoaActivity extends AppCompatActivity {

    private TextView nome;
    private TextView sobrenome;
    private TextView data;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        toolbar = findViewById(R.id.toolbar_pessoa);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nome = findViewById(R.id.pessoa_nome_view);
        sobrenome = findViewById(R.id.pessoa_sobrenome_view);
        data = findViewById(R.id.pessoa_data_view);

        Intent intent = getIntent();
        if(intent.hasExtra("PESSOA")){
            Pessoa pessoa = (Pessoa) intent.getSerializableExtra("PESSOA");
            nome.setText(pessoa.getNome());
            sobrenome.setText(pessoa.getSobrenome());
            data.setText(pessoa.getDataNascimento());
        }
    }
}
