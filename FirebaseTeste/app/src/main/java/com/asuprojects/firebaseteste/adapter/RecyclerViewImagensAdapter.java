package com.asuprojects.firebaseteste.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.asuprojects.firebaseteste.R;
import com.asuprojects.firebaseteste.model.ImagemUrl;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.UUID;

public class RecyclerViewImagensAdapter extends RecyclerView.Adapter<RecyclerViewImagensAdapter.ImagemUrlViewHolder> {

    private Context ctx;
    private List<ImagemUrl> lista;
    private OnClickListeners listeners;

    private StorageReference storage = FirebaseStorage.getInstance().getReference().child("imagens");

    public RecyclerViewImagensAdapter(Context ctx, List<ImagemUrl> lista, OnClickListeners listeners) {
        this.ctx = ctx;
        this.lista = lista;
        this.listeners = listeners;

    }

    public void setLista(List<ImagemUrl> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImagemUrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);


        return new ImagemUrlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagemUrlViewHolder holder, int position) {
        ImagemUrl imagem = lista.get(position);

        Glide.with(ctx)
                .load(imagem.getUrl())
                .apply(new RequestOptions()
                        //.diskCacheStrategy(DiskCacheStrategy.NONE)
                        //.skipMemoryCache(true)
                        .signature(new ObjectKey(UUID.randomUUID())))
                .into(holder.imagem);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class ImagemUrlViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagem;
        private Button btnExcluir;

        public ImagemUrlViewHolder(View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imagem_thumb);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);

            imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listeners.onClickImage(imagem, getAdapterPosition());
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listeners.onDeleteImage(getAdapterPosition());
                }
            });
        }

    }
}
