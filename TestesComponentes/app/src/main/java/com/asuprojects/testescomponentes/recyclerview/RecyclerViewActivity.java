package com.asuprojects.testescomponentes.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.asuprojects.testescomponentes.R;
import com.asuprojects.testescomponentes.database.ListaRepository;

public class RecyclerViewActivity extends AppCompatActivity {

    private FloatingActionButton fabCriarLista;

    private ListaRepository listaRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        listaRepo = new ListaRepository(this);
        listaRepo.deleteAll();

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("RecyclerView");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frameLayout, new ListasFragment());
        tx.commit();

        fabCriarLista = findViewById(R.id.fab_criar_lista);

        fabCriarLista.setVisibility(View.VISIBLE);

        fabCriarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.frameLayout, new ListaItemFragment());
                tx.commit();

                fabCriarLista.setVisibility(View.GONE);
            }
        });



    }

}
