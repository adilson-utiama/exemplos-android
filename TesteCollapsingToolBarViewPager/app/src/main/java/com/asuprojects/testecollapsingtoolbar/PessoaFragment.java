package com.asuprojects.testecollapsingtoolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class PessoaFragment extends Fragment {

    private static final String ARG_PARAM1 = "section_number";
    private static final String LISTA = "lista_pessoas";

    private int mParam1;

    private static ArrayList<Pessoa> listaPessoas;

    public PessoaFragment() {
        // Required empty public constructor
    }


    public static PessoaFragment newInstance(int posicao, ArrayList<Pessoa> lista) {

        listaPessoas = lista;

        PessoaFragment fragment = new PessoaFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, posicao);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pessoa, container, false);

        TextView passo = view.findViewById(R.id.passo);
        TextView nome = view.findViewById(R.id.nome_pessoa);
        TextView email = view.findViewById(R.id.email_pessoa);

        int posicao = getArguments().getInt(ARG_PARAM1);

        Log.i("FRAGMENT_OmCreateView", "Position: " + posicao);

        Pessoa pessoa = listaPessoas.get(posicao);

        Log.i("PESSOA", "Pessoa recuperada: " + pessoa);

        passo.setText("Passo " + (posicao + 1) + " de " + listaPessoas.size());
        nome.setText(pessoa.getNome());
        email.setText(pessoa.getEmail());

        return view;
    }


}
