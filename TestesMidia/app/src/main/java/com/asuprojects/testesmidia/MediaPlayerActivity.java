package com.asuprojects.testesmidia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MediaPlayerActivity extends AppCompatActivity {

    private MediaPlayer player;
    
    private static final int ABRIR_ARQUIVO_REQUEST = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
    }

    public void executarMusicaArquivo(View view){
        player = MediaPlayer.create(this, R.raw.musica);
        player.start();
    }

    public void executarMusicaUrl(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/*");
        startActivityForResult(Intent.createChooser(intent, "Selecionar Midia"), ABRIR_ARQUIVO_REQUEST);
        //startActivityForResult(intent, ABRIR_ARQUIVO_REQUEST);
    }

    public void executar(View view){
        if(!player.isPlaying()){
            player.start();
        }
    }

    public void pausar(View view){
        if(player.isPlaying()){
            player.pause();
        }
    }

    public void parar(View view){
        if(player.isPlaying()){
            player.stop();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ABRIR_ARQUIVO_REQUEST && resultCode == RESULT_OK){

            Uri audio = data.getData();

            liberarPlayer();

            player = MediaPlayer.create(this, audio);

            Log.i("DATA_RECEBIDO", "DATA: " + audio.toString());
            Toast.makeText(this, "Arquivo Recebido com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        liberarPlayer();
    }

    private void liberarPlayer(){
        if(player != null){
            player.release();
        }
    }
}
