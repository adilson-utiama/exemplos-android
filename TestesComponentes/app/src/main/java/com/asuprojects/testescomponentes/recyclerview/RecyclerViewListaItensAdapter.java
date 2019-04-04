package com.asuprojects.testescomponentes.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asuprojects.testescomponentes.R;

import java.util.List;

public class RecyclerViewListaItensAdapter extends RecyclerView.Adapter<RecyclerViewListaItensAdapter.ItemViewHolder> {

    private List<Item> lista;
    private Context ctx;

    public RecyclerViewListaItensAdapter(Context ctx, List<Item> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_holder, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder,final int position) {
        Item item = lista.get(position);
        holder.nomeItem.setText(item.getNome());
        holder.iconRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LISTA", "onClick: ListaSize: " + lista.size() +
                            " | Posicao: " + position);
                try{
                    lista.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                } catch(IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            }
        });
        holder.iconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Editando Item..." + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView nomeItem;
        private ImageView iconRemover;
        private ImageView iconEdit; 

        public ItemViewHolder(View itemView) {
            super(itemView);

            nomeItem = itemView.findViewById(R.id.tx_item);
            iconRemover = itemView.findViewById(R.id.im_remover);
            iconEdit = itemView.findViewById(R.id.im_edit);
        }
    }
}
