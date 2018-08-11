package com.asuprojects.testesmidia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaoFoto = findViewById(R.id.botao_testar_camera_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraFotoActivity.class);
                startActivity(intent);
            }
        });

        Button botaoVideo = findViewById(R.id.botao_testar_camera_video);
        botaoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraVideoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void acessarMediaPlayer(View view){
        Intent intent = new Intent(this, MediaPlayerActivity.class);
        startActivity(intent);
    }

}
