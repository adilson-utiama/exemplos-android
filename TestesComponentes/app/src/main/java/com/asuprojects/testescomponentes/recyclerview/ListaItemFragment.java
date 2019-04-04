package com.asuprojects.testescomponentes.recyclerview;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.asuprojects.testescomponentes.R;
import com.asuprojects.testescomponentes.database.ItemRepository;
import com.asuprojects.testescomponentes.database.ListaRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListaItemFragment extends Fragment {

    private List<Item> lista;
    private AppCompatEditText itemNome;
    private RecyclerViewListaItensAdapter adapter;

    private ItemRepository itemRepo;
    private ListaRepository listaRepo;

    public ListaItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_item, container, false);

        itemRepo = new ItemRepository(getActivity());
        listaRepo = new ListaRepository(getActivity());

        lista = new ArrayList<>();

        itemNome = view.findViewById(R.id.et_item_nome);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lista_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerViewListaItensAdapter(getActivity(), lista);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.action_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionaItem();
            }
        });

        view.findViewById(R.id.action_save_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaLista();
            }
        });

        return view;
    }

    public void adicionaItem(){
        Item item = new Item();
        item.setNome(itemNome.getText().toString());
        if(!itemNome.getText().toString().equals("") ||
                !itemNome.getText().toString().isEmpty()) {
            item.setNome(itemNome.getText().toString());
            lista.add(item);
            adapter.notifyDataSetChanged();
            itemNome.setText("");
        } else {
            Toast.makeText(getActivity(), "Necessario informar nome do Item", Toast.LENGTH_SHORT).show();
        }

    }

    public void salvaLista() {
        new AlertDialog.Builder(getActivity())
                .setMessage("Finalizar e salvar a lista de Itens?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Fecha o teclado de input
                        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(
                                itemNome.getWindowToken(), 0);

                        ListaItens listaItens = new ListaItens();
                        listaItens.setListaItens(lista);
                        listaItens.setNomeLista("Lista-" + Calendar.getInstance().getTimeInMillis());
                        long id = listaRepo.salvaLista(listaItens);
                        for (Item item : lista){
                            item.setListaId(id);
                            itemRepo.adicionaItem(item);
                        }

                        FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.frameLayout, new ListasFragment());
                        tx.commit();

                        getActivity().findViewById(R.id.fab_criar_lista)
                                .setVisibility(View.VISIBLE);

                    }
                })
                .setNegativeButton("NAO", null)
                .show();
    }


}
