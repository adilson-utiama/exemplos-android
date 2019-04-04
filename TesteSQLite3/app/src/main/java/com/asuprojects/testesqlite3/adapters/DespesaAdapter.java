package com.asuprojects.testesqlite3.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asuprojects.testesqlite3.R;
import com.asuprojects.testesqlite3.model.Despesa;

import java.util.List;

public class DespesaAdapter extends RecyclerView.Adapter {


    private List<Despesa> despesas;

    public DespesaAdapter(List<Despesa> despesas) {
        this.despesas = despesas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_despesa, null);
        DespesaViewHolder viewHolder = new DespesaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Despesa despesa = despesas.get(position);
        DespesaViewHolder despesaHolder = (DespesaViewHolder) holder;
        despesaHolder.setDescricao(despesa.getDescricao());
        despesaHolder.setCategoria(despesa.getCategoriaDespesa().getDescricao());
        despesaHolder.setValor(despesa.getValorFormatado());
        despesaHolder.setData(despesa.getDataFormatada());

    }

    @Override
    public int getItemCount() {
        return despesas != null ? despesas.size() : 0;
    }


    public class DespesaViewHolder extends RecyclerView.ViewHolder{

        private TextView descricao;
        private TextView categoria;
        private TextView valor;
        private TextView data;

        public DespesaViewHolder(View itemView) {
            super(itemView);
            descricao = itemView.findViewById(R.id.despesa_descricao);
            categoria = itemView.findViewById(R.id.despesa_categoria);
            valor = itemView.findViewById(R.id.despesa_valor);
            data = itemView.findViewById(R.id.despesa_data);
        }

        public TextView getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao.setText(descricao);
        }

        public TextView getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria.setText(categoria);
        }

        public TextView getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor.setText(valor);
        }

        public TextView getData() {
            return data;
        }

        public void setData(String data) {
            this.data.setText(data);
        }
    }
}
