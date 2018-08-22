package com.asuprojects.exemploandroidroom.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.asuprojects.exemploandroidroom.R;
import com.asuprojects.exemploandroidroom.models.Tarefa;
import com.asuprojects.exemploandroidroom.models.enums.Prioridade;
import com.asuprojects.exemploandroidroom.models.enums.TarefaStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TarefaActivity extends AppCompatActivity {

    private static final String NOVA_TAREFA = "Nova_Tarefa";
    private EditText titulo;
    private EditText descricao;
    private Spinner spinnerPrioridade;
    private Spinner spinnerStatus;
    private FloatingActionButton btnSalvar;

    private List<String> prioridades;
    private List<String> statusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        prioridades = new ArrayList<>();
        for(Prioridade s : Prioridade.values()){
            prioridades.add(s.toString());
        }
        ArrayAdapter adapterPrioridade = new ArrayAdapter<>(this, R.layout.meu_spinner, prioridades);
        adapterPrioridade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusList = new ArrayList<>();
        for (TarefaStatus st : TarefaStatus.values()){
            statusList.add(st.toString());
        }
        ArrayAdapter adapterStatus = new ArrayAdapter<>(this, R.layout.meu_spinner, statusList);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        titulo = findViewById(R.id.et_tarefa_titulo);
        descricao = findViewById(R.id.et_tarefa_descricao);
        spinnerPrioridade = findViewById(R.id.spinner_prioridade);
        spinnerPrioridade.setAdapter(adapterPrioridade);
        spinnerStatus = findViewById(R.id.spinner_status);
        spinnerStatus.setAdapter(adapterStatus);

        btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(titulo.getText().toString());
                tarefa.setDescricao(descricao.getText().toString());
                int position = spinnerPrioridade.getSelectedItemPosition();
                String pri = prioridades.get(position);
                tarefa.setPrioridade(Prioridade.toEnum(pri));
                int position1 = spinnerStatus.getSelectedItemPosition();
                String st = statusList.get(position1);
                tarefa.setStatus(TarefaStatus.toEnum(st));
                tarefa.setDataIncluida(Calendar.getInstance().getTime());

                intent.putExtra(NOVA_TAREFA, tarefa);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }
}
