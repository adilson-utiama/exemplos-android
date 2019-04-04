package com.asuprojects.testearquivos;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.*;
import java.util.Scanner;

import static android.app.Activity.RESULT_OK;

public class ReadFragment extends Fragment {

    static final int REQUEST_CODE = 1;
    private Button btnCarrega;
    private TextView conteudo;

    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        conteudo = view.findViewById(R.id.conteudo);
        btnCarrega = view.findViewById(R.id.btn_read);
        btnCarrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("text/*");
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE){
                Uri uri = data.getData();
                Log.i("URI", "onActivityResult: " + uri.toString());

                StringBuilder builder = new StringBuilder();

                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                    Scanner scanner = new Scanner(inputStream);
                    while(scanner.hasNext()) {
                        String next = scanner.next();
                        builder.append(next);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.i("ERRO", "onActivityResult: Não foi possivel ler conteudo do arquivo");
                }

                conteudo.setText(builder.toString());


            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
