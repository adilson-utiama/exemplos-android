package com.asuprojects.webserviceretrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText inputCep;
    private Button btnConsulta;
    private TextView resposta;

    private boolean conectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCep = findViewById(R.id.input_cep);
        resposta = findViewById(R.id.resposta);
        btnConsulta = findViewById(R.id.btn_consulta);
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verificaConexaoComInternet();

                if(conectado){
                    btnConsulta.setEnabled(false);

                    Call<Cep> call = new RetrofitConfig().getCepService().buscarCep(inputCep.getText().toString());
                    call.enqueue(new Callback<Cep>() {
                        @Override
                        public void onResponse(Call<Cep> call, Response<Cep> response) {
                            Cep cep = response.body();
                            resposta.setText(cep.toString());
                            btnConsulta.setEnabled(true);
                        }

                        @Override
                        public void onFailure(Call<Cep> call, Throwable t) {
                            resposta.setText("Não foi possivel consultar CEP...");
                            btnConsulta.setEnabled(true);
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Não há conexao com Internet",
                            Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void verificaConexaoComInternet() {
        ConnectivityManager conectivty = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conectivty.getActiveNetworkInfo() != null
                && conectivty.getActiveNetworkInfo().isAvailable()
                && conectivty.getActiveNetworkInfo().isConnected()){
            conectado = true;
        }
    }
}
