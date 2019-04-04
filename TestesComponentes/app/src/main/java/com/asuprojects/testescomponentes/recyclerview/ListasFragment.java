package com.asuprojects.testescomponentes.recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.asuprojects.testescomponentes.R;
import com.asuprojects.testescomponentes.database.ItemRepository;
import com.asuprojects.testescomponentes.database.ListaRepository;
import com.asuprojects.testescomponentes.recyclerview.impl.VisualizarOnClickListener;

import java.util.List;

public class ListasFragment extends Fragment {

    private List<ListaItens> listas;

    private ItemRepository itemRepo;
    private ListaRepository listaRepo;

    public ListasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listas, container, false);

        listaRepo = new ListaRepository(getActivity());
        itemRepo = new ItemRepository(getActivity());

        listas = listaRepo.getAllLists();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lista);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerViewListasAdapter adapter = new RecyclerViewListasAdapter(getActivity(), listas);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setListeners(new VisualizarOnClickListener() {
            @Override
            public void onClickVisualizarLista(View view, int position) {
                ListaItens listaItens = listas.get(position);
                List<Item> itensLista = itemRepo.getItensPorListaId(listaItens.getId());
                Toast.makeText(getActivity(),
                        "Visualizando Lista... ID: " + listaItens.getId() + "\n" +
                                "Lista: " + listaItens.getNomeLista() + "\n"
                            + itensLista,
                        Toast.LENGTH_LONG).show();
            }

        });

        return view;
    }

}
