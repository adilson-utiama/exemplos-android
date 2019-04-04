package com.asuprojects.swipesimplesexemplo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.asuprojects.swipesimplesexemplo.adapter.PalavraAdapter;
import com.asuprojects.swipesimplesexemplo.model.Palavra;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAdiciona;

    private List<Palavra> lista = new ArrayList<>();
    private PalavraAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdiciona = findViewById(R.id.fabAdicionar);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        inserePalavras();

        adapter = new PalavraAdapter(lista);
        recyclerView.setAdapter(adapter);

        //Adiciona Swipe
        configuraSwipe();

        fabAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_adiciona, null);
                final EditText edit = dialog.findViewById(R.id.editPalavra);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Adicionar palavra")
                        .setView(dialog)
                        .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(!edit.getText().toString().isEmpty()) {
                                    String conteudo = edit.getText().toString();
                                    lista.add(new Palavra(conteudo));
                                    adapter.notifyDataSetChanged();
                                }else{
                                    Toast.makeText(MainActivity.this, "Digite uma palavra", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();

            }
        });

    }

    private void configuraSwipe() {

        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE; //desativado drag and drop
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                String direcao = "";
                if(i == ItemTouchHelper.START) {
                    Log.i("TESTE", "onSwiped: Start " + i + " Esquerda");
                    direcao = "ESQUERDA";
                }
                if(i == ItemTouchHelper.END) {
                    Log.i("TESTE", "onSwiped: End " + i + " Direita");
                    direcao = "DIREITA";
                }
                int position = viewHolder.getAdapterPosition();
                Palavra palavra = lista.get(position);
                Toast.makeText(MainActivity.this, palavra.getConteudo() + " Arrastado para " + direcao,
                        Toast.LENGTH_SHORT).show();

                lista.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);
    }

    private void inserePalavras() {
        lista.add(new Palavra("Palavra 1"));
        lista.add(new Palavra("Palavra 2"));
        lista.add(new Palavra("Palavra 3"));
        lista.add(new Palavra("Palavra 4"));
    }
}
