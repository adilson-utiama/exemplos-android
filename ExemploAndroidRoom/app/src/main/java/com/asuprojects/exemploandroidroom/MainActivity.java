package com.asuprojects.exemploandroidroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.asuprojects.exemploandroidroom.adapter.TarefaListaAdapter;
import com.asuprojects.exemploandroidroom.models.Tarefa;
import com.asuprojects.exemploandroidroom.models.viewmodel.TarefaViewModel;
import com.asuprojects.exemploandroidroom.ui.TarefaActivity;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NOVA_TAREFA_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private TarefaListaAdapter adapter;

    private TarefaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new TarefaListaAdapter(this, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(TarefaViewModel.class);
        viewModel.getTodasTarefas().observe(this, new Observer<List<Tarefa>>() {
            @Override
            public void onChanged(@Nullable List<Tarefa> tarefas) {
                adapter.setListaTarefas(tarefas);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
                startActivityForResult(intent, NOVA_TAREFA_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == NOVA_TAREFA_REQUEST_CODE && resultCode == RESULT_OK){
            Tarefa tarefa = (Tarefa) data.getSerializableExtra("Nova_Tarefa");
            viewModel.adiciona(tarefa);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
