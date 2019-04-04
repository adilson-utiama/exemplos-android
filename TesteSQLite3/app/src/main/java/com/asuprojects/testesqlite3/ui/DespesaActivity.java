package com.asuprojects.testesqlite3.ui;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.asuprojects.testesqlite3.MainActivity;
import com.asuprojects.testesqlite3.R;
import com.asuprojects.testesqlite3.converters.BigDecimalConverter;
import com.asuprojects.testesqlite3.converters.CalendarConverter;
import com.asuprojects.testesqlite3.dao.DespesaDAO;
import com.asuprojects.testesqlite3.helper.CategoriaUtil;
import com.asuprojects.testesqlite3.model.Categoria;
import com.asuprojects.testesqlite3.model.Despesa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DespesaActivity extends AppCompatActivity {

    private TextInputEditText descricao;
    private TextInputEditText valor;
    private AppCompatSpinner spinner;
    private AppCompatButton btnSalvar;

    private static AppCompatButton btnData;

    private List<String> listaCategorias;

    private DespesaDAO dao;

    private Despesa despesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        dao = new DespesaDAO(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adiciona");

        descricao = findViewById(R.id.campo_descricao);
        valor = findViewById(R.id.campo_valor);
        spinner = findViewById(R.id.campo_spinner);
        btnData = findViewById(R.id.campo_data);
        btnData.setText(CalendarConverter.toStringFormatada(Calendar.getInstance()));

        btnSalvar = findViewById(R.id.btn_salvar);

        listaCategorias = new ArrayList<>();
        for(Categoria c : Categoria.values()){
            listaCategorias.add(c.getDescricao());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCategorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(despesa == null){
                    despesa = new Despesa();
                }

                despesa.setDescricao(descricao.getText().toString());
                despesa.setData(CalendarConverter.toCalendar(btnData.getText().toString()));
                int position = spinner.getSelectedItemPosition();

                despesa.setCategoriaDespesa(CategoriaUtil.getCategoriaFrom(listaCategorias.get(position)));
                despesa.setValor(BigDecimalConverter.toBigDecimal(valor.getText().toString()));

                dao.insertOrUpdate(despesa);

                Intent intent = new Intent(DespesaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if(intent.hasExtra("EDITAR_DESPESA")){
            despesa = (Despesa) intent.getSerializableExtra("EDITAR_DESPESA");
            descricao.setText(despesa.getDescricao());
            valor.setText(String.valueOf(despesa.getValor().doubleValue()));
            btnData.setText(despesa.getDataFormatada());

            int index = listaCategorias.indexOf(despesa.getCategoriaDespesa().getDescricao());
            spinner.setSelection(index, true);

            btnSalvar.setText("Atualizar");
            getSupportActionBar().setTitle("Edição");
        }

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show( getSupportFragmentManager(),"datePicker");

            }
        });

    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

    //Inner Class
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
            StringBuilder builder = new StringBuilder();
            builder.append(dia).append("/").append(mes + 1).append("/").append(ano);
            Log.i("DATA_CAPTURADA: ", builder.toString());
            btnData.setText(builder.toString());

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }
    }
}
