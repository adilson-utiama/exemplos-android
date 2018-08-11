package com.asuprojects.testecollapsingtoolbar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class PessoaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private PessoaOnClickListener listener;

    private TextView nome;
    private TextView sobrenome;
    private TextView data;

    public PessoaViewHolder(View itemView, PessoaOnClickListener listener) {
        super(itemView);
        this.listener = listener;

        itemView.setOnClickListener(this);
        nome = itemView.findViewById(R.id.pessoa_nome);
        sobrenome = itemView.findViewById(R.id.pessoa_sobrenome);
        data = itemView.findViewById(R.id.pessoa_data);
    }

    public TextView getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.setText(nome);
    }

    public TextView getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome.setText(sobrenome);
    }

    public TextView getData() {
        return data;
    }

    public void setData(String data) {
        this.data.setText(data);
    }

    @Override
    public void onClick(View view) {

        if(this.listener != null){
            this.listener.onClick(getAdapterPosition());
        }
    }
}
