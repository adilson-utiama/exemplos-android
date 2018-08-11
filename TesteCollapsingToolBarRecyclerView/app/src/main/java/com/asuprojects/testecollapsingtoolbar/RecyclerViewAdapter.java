package com.asuprojects.testecollapsingtoolbar;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<PessoaViewHolder> {

    private PessoaOnClickListener listener;
    private List<Pessoa> listaPessoas;

    public void setOnClickListener(PessoaOnClickListener listener){
        this.listener = listener;
    }

    public RecyclerViewAdapter(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    @Override
    public PessoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PessoaViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_pessoa, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(PessoaViewHolder holder, int position) {

        Log.i("PESSOA_ADICIONADA", "Position: " + position);

        Pessoa pessoa = listaPessoas.get(position);
        holder.setNome(pessoa.getNome());
        holder.setSobrenome(pessoa.getSobrenome());
        holder.setData(pessoa.getDataNascimento());
    }

    @Override
    public int getItemCount() {
        return listaPessoas != null ? listaPessoas.size() : 0;
    }
}
