package com.asuprojects.firebaseteste;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.asuprojects.firebaseteste.model.ImagemUrl;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.UUID;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class ImageUploadFragment extends Fragment {

    private static final int CHOOSE_IMAGE = 100;
    private Button btnLoadImage;
    private Button btnUploadImage;
    private ImageView image;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("imagens_url");
    private Random random = new Random();

    private ProgressBar progressBar;

    private boolean imagemCarregada = false;

    private String nomeArquivo;

    public ImageUploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_upload, container, false);

        btnLoadImage = view.findViewById(R.id.btnLoadImage);
        btnUploadImage = view.findViewById(R.id.btnUploadImage);
        image = view.findViewById(R.id.imageView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setMax(100);

        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_IMAGE);
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(auth.getCurrentUser() != null) {

                    if(imagemCarregada) {
                        progressBar.setVisibility(View.VISIBLE);
                        bloquearBotao(true);
                        fazUploadDaImagem();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == CHOOSE_IMAGE) {
                Uri dataUri = data.getData();
                Log.i("DATA_URI", "onActivityResult: " + dataUri);
                image.setImageURI(dataUri);
                imagemCarregada = true;
            } else {
                imagemCarregada = false;
            }
        } else if(resultCode == RESULT_CANCELED) {
            imagemCarregada = false;
        }
    }

    private void fazUploadDaImagem() {
        //Configuracao para imagem ser salva em memoria
        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();

        //Recupera bitmap da imagem
        Bitmap bitmap = image.getDrawingCache();

        //Compressao da imagem
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

        //Converte o ByteArrayOutputStream para pixels brutos em um array de bytes
        //(dados da image)
        byte[] dadosImagem = baos.toByteArray();

        //Define nós para o storage
        final StorageReference reference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = reference.child("imagens");

        UUID uuid = UUID.randomUUID();
        nomeArquivo = uuid.toString().concat(".jpg");

        final StorageReference imagemRef = imagens.child(nomeArquivo);

        //Retorna objeto que ira controlar o upload
        final UploadTask uploadTask = imagemRef.putBytes(dadosImagem);
        uploadTask.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Falha no upload: " + e.getMessage().toString(),
                        Toast.LENGTH_LONG).show();

                progressBar.setProgress(100);
                progressBar.setVisibility(View.GONE);
                image.destroyDrawingCache();
                bloquearBotao(false);
            }
        }).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imagemRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(getActivity(),
                                "Upload realizado com sucesso \n URL: " + uri.toString(),
                                Toast.LENGTH_LONG).show();

                        salvaUrl(uri);
                        Log.i("IMAGENS_DOWNLOAD", "onSuccess: " + uri.toString());

                        progressBar.setProgress(100);
                        progressBar.setVisibility(View.GONE);
                        image.destroyDrawingCache();
                        bloquearBotao(false);
                    }
                });


            }
        });

    }

    private void salvaUrl(Uri uri) {
        ImagemUrl imagemUrl = new ImagemUrl(nomeArquivo, uri.toString());
        reference.push().setValue(imagemUrl);
    }

    private void bloquearBotao(boolean ativo) {
        if(ativo) {
            btnUploadImage.setEnabled(false);
        } else {
            btnUploadImage.setEnabled(true);
        }
    }
}
