package com.asuprojects.testesqlite3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.asuprojects.testesqlite3.R;

public class FirstTimeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextInputLayout inputLayoutUsuario;
    private TextInputEditText inputEditTextUsuario;
    private TextInputLayout inputLayoutSenha;
    private TextInputEditText inputEditTextSenha;
    private TextInputLayout inputLayoutRepeteSenha;
    private TextInputEditText inputEditTextRepeteSenha;
    private AppCompatSpinner spinnerPergunta;
    private TextInputEditText inputEditTextRespostaPergunta;
    private AppCompatButton btnCadastro;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean primeiraVez = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        preferences = getSharedPreferences(getString(R.string.financas_preferences), Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean(getString(R.string.primeiro_uso), primeiraVez);
        editor.apply();

        inputLayoutUsuario = findViewById(R.id.first_input_layout_user);
        inputEditTextUsuario = findViewById(R.id.first_input_user);
        inputLayoutSenha = findViewById(R.id.first_input_layout_password);
        inputEditTextSenha = findViewById(R.id.first_input_password);
        inputLayoutRepeteSenha = findViewById(R.id.first_input_layout_password_repeat);
        inputEditTextRepeteSenha = findViewById(R.id.first_input_password_repeat);
        inputEditTextRespostaPergunta = findViewById(R.id.input_pergunta_resposta);
        spinnerPergunta = findViewById(R.id.spinner_pergunta);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.perguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPergunta.setAdapter(adapter);
        spinnerPergunta.setOnItemSelectedListener(this);

        btnCadastro = findViewById(R.id.btn_cadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primeiraVez = false;
                editor = preferences.edit();
                editor.putBoolean(getString(R.string.primeiro_uso), primeiraVez);
                editor.apply();

                //TODO salvar usuario no banco

                Intent intent = new Intent(FirstTimeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Relacionado ao Spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long id) {
        String perguntaSelecionada = (String) adapterView.getItemAtPosition(posicao);
        Log.i("SPINNER_SELECAO: ", perguntaSelecionada);
    }

    //Relacionado ao Spinner
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
