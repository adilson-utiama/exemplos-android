package com.asuprojects.exemploandroidroom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asuprojects.exemploandroidroom.R;
import com.asuprojects.exemploandroidroom.models.Tarefa;

import java.text.SimpleDateFormat;
import java.util.List;

public class TarefaListaAdapter extends RecyclerView.Adapter<TarefaListaAdapter.TarefaViewHolder> {

    private List<Tarefa> lista;
    private final LayoutInflater layoutInflater;

    public TarefaListaAdapter(Context context, List<Tarefa> lista) {
        this.lista = lista;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public TarefaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TarefaViewHolder holder, int position) {
        if(lista != null){
            Tarefa tarefa = lista.get(position);
            holder.titulo.setText(tarefa.getTitulo());
            holder.descricao.setText(tarefa.getDescricao());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            holder.data.setText(format.format(tarefa.getDataIncluida()));
            holder.prioridade.setText(tarefa.getPrioridade().toString());
            holder.status.setText(tarefa.getStatus().toString());
        }
    }

    public void setListaTarefas(List<Tarefa> tarefasLista){
        this.lista = tarefasLista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView descricao;
        private TextView data;
        private TextView status;
        private TextView prioridade;

        public TarefaViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tv_tarefa_titulo);
            descricao = itemView.findViewById(R.id.tv_tarefa_descricao);
            data = itemView.findViewById(R.id.tv_tarefa_data);
            status = itemView.findViewById(R.id.tv_tarefa_status);
            prioridade = itemView.findViewById(R.id.tv_tarefa_prioridade);
        }
    }
}
