package com.asuprojects.testesmidia;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class CameraVideoActivity extends AppCompatActivity {

    private static final int CAPTURAR_VIDEO = 2;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_video);

    }

    public void visualizarVideo(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "video/mp4");
        startActivity(intent);
    }

    public void capturarVideo(View view){
        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String nomeVideo = diretorio.getPath() + "/" + System.currentTimeMillis() + ".mp4";
        uri = Uri.fromFile(new File(nomeVideo));

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        startActivityForResult(intent, CAPTURAR_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAPTURAR_VIDEO){
            if(resultCode == RESULT_OK){
                String msg = "Video gravado em " + data.getDataString();
                mostrarMensagem(msg);
                uri = data.getData();
            }else{
                mostrarMensagem("Video não Gravado");
            }
        }
    }

    private void mostrarMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
