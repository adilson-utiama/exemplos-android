package com.asuprojects.firebaseteste;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.asuprojects.firebaseteste.adapter.OnClickListeners;
import com.asuprojects.firebaseteste.adapter.RecyclerViewImagensAdapter;
import com.asuprojects.firebaseteste.model.ImagemUrl;
import com.asuprojects.firebaseteste.model.Produto;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class ImageListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ImagemUrl> imgs = new ArrayList<>();
    private RecyclerViewImagensAdapter adapter;

    private StorageReference imagens =
            FirebaseStorage.getInstance().getReference().child("imagens");

    private DatabaseReference imagens_url;


    public ImageListFragment() {
        imagens_url = FirebaseDatabase.getInstance().getReference().child("imagens_url");
        imagens_url.keepSynced(true);

        imgs = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_image_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewImagens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

//        imagens_url.removeValue().addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.i("LISTA_SIZE", "onSuccess: removeValue");
//            }
//        });


        imagens_url.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, ImagemUrl>> type = new GenericTypeIndicator<HashMap<String, ImagemUrl>>() {};

                if(dataSnapshot.getValue() != null) {
                    imgs = new ArrayList<>();

                    HashMap<String, ImagemUrl> value = dataSnapshot.getValue(type);

                    for(String key : value.keySet()) {
                        ImagemUrl imagemUrl = value.get(key);
                        imgs.add(new ImagemUrl(key, imagemUrl.getNome(), imagemUrl.getUrl()));
                    }

                    Log.i("LISTA_SIZE", "onDataChange: " + imgs.size());

                    configuraAdapter();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void configuraAdapter() {
        adapter = new RecyclerViewImagensAdapter(getActivity(), imgs, new OnClickListeners() {
            @Override
            public void onDeleteImage(final int position) {
                final ImagemUrl img = imgs.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Confirmação de exclusao de imagem");
                dialog.setMessage("Deletar imagem " + img.getNome() + " ?");
                dialog.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluirImagem(img);
                        //imgs.remove(position);
                        //adapter.notifyItemRemoved(position);
                    }
                }).setNegativeButton("Cancelar", null)
                        .show();
            }

            @Override
            public void onClickImage(ImageView imageView, int position) {
                ImagemUrl img = imgs.get(position);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(img.getUrl()),"image/*");
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void excluirImagem(ImagemUrl img) {
        imagens.child(img.getNome()).delete().addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(), "Imagem excluida com sucesso.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Erro ao excluir imagem: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        imagens_url.child(img.getId()).removeValue().addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i("LISTA_SIZE", "onSuccess: removido com sucesso");
            }
        }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LISTA_SIZE", "onFailure: " + e.getMessage());
            }
        });

    }

}
