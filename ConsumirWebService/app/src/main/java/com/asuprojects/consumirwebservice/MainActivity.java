package com.asuprojects.consumirwebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText inputCep;
    private Button btnConsulta;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCep = findViewById(R.id.input_cep);
        btnConsulta = findViewById(R.id.btn_consulta);
        resultado = findViewById(R.id.resultado);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                try{
                    Cep cep = new HttpService(inputCep.getText().toString()).execute().get();
                    resultado.setText(cep.toString());
                }catch(Exception e){
                    resultado.setText("Não foi possivel obter informações...");
                    e.printStackTrace();
                }
            }
        });
        

    }
}
