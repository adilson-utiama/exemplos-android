package com.asuprojects.testearquivos;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import java.io.File;

public class DiretoriosFragment extends Fragment {


    private static final int REQUEST_DIRECTORY = 999;

    public DiretoriosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diretorios, container, false);

        TextView filesdir = view.findViewById(R.id.getfilesdir);
        TextView externalFilesDir = view.findViewById(R.id.externalFilesDir);
        TextView extStorageFilesDir = view.findViewById(R.id.externalStorageFilesDir);
        TextView datadirectory = view.findViewById(R.id.datadirectory);
        TextView externalMediaDirs = view.findViewById(R.id.getExternalMediaDirs);

        Button loadFolder = view.findViewById(R.id.btn_load_folder);
        loadFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent chooserIntent = new Intent(getActivity(), DirectoryChooserActivity.class);
                final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                        .newDirectoryName("DirChooserSample")
                        .allowReadOnlyDirectory(true)
                        .allowNewDirectoryNameModification(true)
                        .build();
                chooserIntent.putExtra(DirectoryChooserActivity.EXTRA_CONFIG, config);
                startActivityForResult(chooserIntent, REQUEST_DIRECTORY);
            }
        });

        File filesDirectory = getActivity().getFilesDir();
        filesdir.setText(filesDirectory.getAbsolutePath());

        File dataDirectory = Environment.getDataDirectory();
        datadirectory.setText(dataDirectory.getAbsolutePath());

        File extFilesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        externalFilesDir.setText(extFilesDir.getAbsolutePath());

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        extStorageFilesDir.setText(externalStorageDirectory.getAbsolutePath());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            File[] extMediaDirs = getActivity().getExternalMediaDirs();
            externalMediaDirs.setText(extMediaDirs.toString());
        } else {
            externalMediaDirs.setText("Não Disponivel");
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DIRECTORY) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
                String stringExtra = data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);
                Toast.makeText(getActivity(), "Result: " + stringExtra, Toast.LENGTH_SHORT).show();
            } else {
                // Nothing selected
            }
        }
    }
}
