package com.saikat.downloader_v2;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText selectionText;
    private String url5mb = "https://firebasestorage.googleapis.com/v0/b/myblog-18d9c.appspot.com/o/Handwashing.pdf?alt=media&token=4f29f8e1-7e51-42a2-ae40-3449be1b7ced";
    private String url33mb = "https://firebasestorage.googleapis.com/v0/b/myblog-18d9c.appspot.com/o/2019.pdf?alt=media&token=947408ea-6731-4350-81ef-6ce4e2a52c96";
    private ProgressBar downloadImagesProgress;
    NonUITaskFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectionText = findViewById(R.id.downloadURL);
        downloadImagesProgress = findViewById(R.id.downloadProgress);
        selectionText.setText(url33mb);

        if(savedInstanceState == null){
            fragment = new NonUITaskFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(fragment,"TaskFragment").commit();
        }else {
            fragment = (NonUITaskFragment) getSupportFragmentManager().findFragmentByTag("TaskFragment");
        }
        if(fragment != null){
            if(fragment.myTask != null
                && fragment.myTask.getStatus() == AsyncTask.Status.RUNNING){
                downloadImagesProgress.setVisibility(View.VISIBLE);
            }
        }

    }


    public void downloadImage(View view) {
        if (selectionText.getText().toString() != null
                && selectionText.getText().toString().length() > 0) {
            fragment.beginTask(url33mb);
        }
    }

    public void updateProgress(int progress){
        downloadImagesProgress.setProgress(progress);
    }

    public void showProgressBarBeforeDownloading(){
        if(fragment.myTask != null){
            if(downloadImagesProgress.getVisibility() != View.VISIBLE
            && downloadImagesProgress.getProgress() != downloadImagesProgress.getMax()){
                downloadImagesProgress.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hideProgressBarAfterDownloading(){
        if(fragment.myTask != null){
            if(downloadImagesProgress.getVisibility() == View.VISIBLE){
                downloadImagesProgress.setVisibility(View.GONE);
            }
        }
    }


}


