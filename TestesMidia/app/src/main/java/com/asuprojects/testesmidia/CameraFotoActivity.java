package com.asuprojects.testesmidia;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class CameraFotoActivity extends AppCompatActivity {

    private static final int CAPTURAR_IMAGEM = 1;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURAR_IMAGEM){
            if(resultCode == RESULT_OK){
                mostrarMensagem("Imagem capturada!");
                adicionaNaGaleria();
            }
        }
    }

    private void adicionaNaGaleria() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    private void mostrarMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void capturarImagem(View view){
        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String nomeImagem = diretorio.getPath() + "/" + System.currentTimeMillis() + ".jpg";
        uri = Uri.fromFile(new File(nomeImagem));

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAPTURAR_IMAGEM);
    }

    public void visualizarImagem(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "image/jpeg");
        startActivity(intent);
    }

    public void escolherOpcao(View view){
        if(view.getId() == R.id.camera){
            Intent intent = new Intent(this, CameraFotoActivity.class);
            startActivity(intent);
        }
    }
}
