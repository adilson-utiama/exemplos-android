package com.asuprojects.swipesimplesexemplo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.asuprojects.swipesimplesexemplo.R;
import com.asuprojects.swipesimplesexemplo.model.Palavra;


import java.util.List;

public class PalavraAdapter extends RecyclerView.Adapter<PalavraAdapter.PalavraViewHolder> {

    private List<Palavra> lista;

    public PalavraAdapter(List<Palavra> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public PalavraViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_palavra, viewGroup, false);
        return new PalavraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalavraViewHolder viewHolder, int position) {

        Palavra palavra = lista.get(position);

        viewHolder.conteudo.setText(palavra.getConteudo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class PalavraViewHolder extends RecyclerView.ViewHolder{

        private TextView conteudo;

        public PalavraViewHolder(@NonNull View itemView) {
            super(itemView);

            conteudo = itemView.findViewById(R.id.textPalavra);
        }
    }
}
