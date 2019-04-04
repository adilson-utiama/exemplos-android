package com.asuprojects.testesqlite3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;

import com.asuprojects.testesqlite3.MainActivity;
import com.asuprojects.testesqlite3.R;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout inputLayoutUser;
    private TextInputEditText inputUser;

    private TextInputLayout inputLayoutPassword;
    private TextInputEditText inputPassword;

    private AppCompatButton btnLogin;
    private AppCompatCheckBox checkbox;

    private boolean manterConectado;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences(getString(R.string.manter_conectado), Context.MODE_PRIVATE);

        inputLayoutUser = findViewById(R.id.input_layout_user);
        inputUser = findViewById(R.id.input_text_user);
        inputLayoutPassword = findViewById(R.id.input_layout_password);
        inputPassword = findViewById(R.id.input_text_password);
        checkbox = findViewById(R.id.checkBox_manter_conectado);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO logar usuario fazendo validacao
                logarUsuario();

                editor = preferences.edit();
                editor.putBoolean(getString(R.string.manter_conectado), checkbox.isChecked() ? true : false);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void logarUsuario() {
        String usuario = inputUser.getText().toString();
        String senha = inputPassword.getText().toString();
        if(checkbox.isChecked()){
            manterConectado = true;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("USER: ").append(usuario)
                .append(" PASSWORD: ").append(senha)
                .append(" MANTER_CONECTADO: ").append(manterConectado);
        Log.i("LOGIN -> ", builder.toString());
    }
}
