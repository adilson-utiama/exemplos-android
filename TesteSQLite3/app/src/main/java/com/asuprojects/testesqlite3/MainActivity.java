package com.asuprojects.testesqlite3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.asuprojects.testesqlite3.adapters.DespesaAdapter;
import com.asuprojects.testesqlite3.converters.BigDecimalConverter;
import com.asuprojects.testesqlite3.dao.DespesaDAO;
import com.asuprojects.testesqlite3.helper.RecyclerItemClickListener;
import com.asuprojects.testesqlite3.model.Despesa;
import com.asuprojects.testesqlite3.ui.DespesaActivity;

import java.math.BigDecimal;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DespesaAdapter adapter;
    private TextView valorTotal;

    private List<Despesa> despesas;

    private DespesaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MyMoney");

        dao = new DespesaDAO(getApplicationContext());

        valorTotal = findViewById(R.id.valor_total);

        despesas = dao.getAll(); //Aqui recupera do banco

        calculaValorTotal();

        recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DespesaAdapter(despesas);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Despesa despesa = despesas.get(position);
                        Despesa despesaEditar = dao.findOne(despesa.get_id());
                        Intent intent = new Intent(MainActivity.this, DespesaActivity.class);
                        intent.putExtra("EDITAR_DESPESA", despesaEditar);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Despesa despesa = despesas.get(position);
                        mostrarDialogDeRemocao(despesa, position);

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DespesaActivity.class);
                startActivity(intent);
            }
        });

    }

    private void calculaValorTotal() {
        BigDecimal total = new BigDecimal(0);
        for(Despesa d : despesas){
            total = total.add(d.getValor());
        }
        valorTotal.setText("Valor Total: " + BigDecimalConverter.toStringFormatado(total));
    }

    private void mostrarDialogDeRemocao(final Despesa despesa, final int posicao){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.DialogCustom);
        dialog.setTitle("Remoção de Despesa");
        dialog.setMessage("APAGAR " + despesa.getDescricao() + " ?");
        dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(despesa.get_id());
                despesas.remove(posicao);
                adapter.notifyItemRemoved(posicao);
                calculaValorTotal();
            }
        });
        dialog.setNegativeButton("NÂO", null);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }
}
