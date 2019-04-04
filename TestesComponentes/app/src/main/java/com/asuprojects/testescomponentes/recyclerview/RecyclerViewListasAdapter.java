package com.asuprojects.testescomponentes.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuprojects.testescomponentes.R;
import com.asuprojects.testescomponentes.recyclerview.impl.VisualizarOnClickListener;

import java.util.List;

public class RecyclerViewListasAdapter extends RecyclerView.Adapter<RecyclerViewListasAdapter.ListaViewHolder>
                implements VisualizarOnClickListener {

    private List<ListaItens> lista;
    private Context ctx;
    private VisualizarOnClickListener listeners;

    public RecyclerViewListasAdapter(Context ctx, List<ListaItens> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    public void setListeners(VisualizarOnClickListener listeners) {
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_view_holder, parent, false);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        ListaItens listaItens = lista.get(position);
        holder.listaNome.setText(listaItens.getNomeLista());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClickVisualizarLista(View view, int position) {

    }

    class ListaViewHolder extends RecyclerView.ViewHolder
                        implements View.OnClickListener{

        private TextView listaNome;
        private ImageView visualizarLista;
        private ImageView editarLista;
        private ImageView deletarLista;

        public ListaViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            listaNome = itemView.findViewById(R.id.lista_nome);
            visualizarLista = itemView.findViewById(R.id.visualizar);
            editarLista = itemView.findViewById(R.id.edit_list);
            deletarLista = itemView.findViewById(R.id.delete_list);

        }

        @Override
        public void onClick(View v) {
            if(listeners != null){
                listeners.onClickVisualizarLista(v, getAdapterPosition());
            }
        }
    }
}
