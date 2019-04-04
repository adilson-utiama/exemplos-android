package com.asuprojects.mobilevisionsamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.asuprojects.mobilevisionsamples.facemapping.photo.PhotoViewerActivity;
import com.asuprojects.mobilevisionsamples.facetracker.FaceTrackerActivity;
import com.asuprojects.mobilevisionsamples.googleeye.GooglyEyesActivity;
import com.asuprojects.mobilevisionsamples.multifacetracker.MultiTrackerActivity;
import com.asuprojects.mobilevisionsamples.ocrreader.OcrCaptureActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_face_mapping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhotoViewerActivity.class));
            }
        });

        findViewById(R.id.btn_face_tracker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FaceTrackerActivity.class));
            }
        });

        findViewById(R.id.btn_multi_tracker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultiTrackerActivity.class));
            }
        });

        findViewById(R.id.btn_ocr_reader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OcrCaptureActivity.class));
            }
        });

        findViewById(R.id.btn_google_eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GooglyEyesActivity.class));
            }
        });

        findViewById(R.id.btn_barcode_scanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BarcodeMainActivity.class));
            }
        });
    }
}
