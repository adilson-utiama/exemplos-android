package com.asuprojects.testearquivos;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.asuprojects.testearquivos.helper.Permissoes;
import com.asuprojects.testearquivos.helper.SharedPrefsUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFragment extends Fragment {

    private TextView caminhoArquivo;
    private EditText nomeArquivo;
    private EditText conteudoArquivo;
    private Button btnInternal;
    private Button btnExternal;
    private RadioGroup radioGroup;

    private String formato = ".txt";
    private SharedPrefsUtil prefs;

    private String[] permissoes = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public WriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        prefs = new SharedPrefsUtil(getActivity());

        Permissoes.validarPermissoes(permissoes, getActivity(), 1);

        caminhoArquivo = view.findViewById(R.id.caminho_arquivo);
        nomeArquivo = view.findViewById(R.id.nome_arquivo);
        conteudoArquivo = view.findViewById(R.id.conteudo_arquivo);
        radioGroup = view.findViewById(R.id.radioGroup);

        btnInternal = view.findViewById(R.id.btnInternal);
        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificaRadioGroup();
                File caminho = getContext().getFilesDir();
                String nome = nomeArquivo.getText().toString();
                nome = nome + formato;

                String completo = caminho + "/" + nome;
                FileOutputStream outputStream;

                String content = conteudoArquivo.getText().toString();

                try {
                    outputStream = getContext().openFileOutput(nome, Context.MODE_PRIVATE);
                    outputStream.write(content.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                caminhoArquivo.setText(completo);
            }
        });
        btnExternal = view.findViewById(R.id.btnExternal);
        btnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!prefs.readBoolean("permissao")) {
                    if(isExternalStorageWritable()){

                        if(nomeArquivo.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), "Informe nome do arquivo", Toast.LENGTH_SHORT).show();
                        } else {
                            verificaRadioGroup();
                            String nome = nomeArquivo.getText().toString();
                            nome = nome + formato;

                            String content = conteudoArquivo.getText().toString();

                            File file = new File(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DOWNLOADS), nome);

                            Log.i("ERRO", "onClick: " + file.getAbsolutePath());

                            try {
                                FileOutputStream outputStream = new FileOutputStream(file);
                                outputStream.write(content.getBytes());
                                outputStream.close();

                            } catch (FileNotFoundException e) {
                                Log.i("ERRO", "onClick: " + e.getMessage());
                            } catch (IOException e) {
                                Log.i("ERRO", "onClick: " + e.getMessage());
                            }

                            caminhoArquivo.setText(file.getAbsolutePath());

                        }

                    } else {
                        Log.i("ERRO", "Falha ao salvar Arquivo");
                    }
                } else {
                    Toast.makeText(getActivity(), "Permissao de escrita não concedida", Toast.LENGTH_SHORT).show();
                }

            }
        });

        verificaRadioGroup();

        return view;
    }

    private void verificaRadioGroup() {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        switch(radioButtonId){
            case R.id.txt:
                formato = ".txt";
                break;
            case R.id.csv:
                formato = ".csv";
                break;
            case R.id.html:
                formato = ".html";
                break;
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int permissao : grantResults) {
            if(permissao != PackageManager.PERMISSION_GRANTED) {
                prefs.putBoolean("permissao", false);
            } else {
                prefs.putBoolean("permissao", true);
            }
        }
    }
}
