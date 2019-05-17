package com.saikat.downloader_v2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTask extends AsyncTask<String ,Integer, Boolean> {

    private int contentLength = -1;
    private int counter = 0;
    private int calculatedProgress = 0;
    private Activity activity;

    public MyTask(Activity activity) {
        onAttach(activity);
    }

    public void onAttach(Activity activity){
        this.activity = activity;
    }

    public void onDetach(){
        activity = null;
    }

    @Override
    protected void onPreExecute() {
        if (activity != null) {
            ((MainActivity)activity).showProgressBarBeforeDownloading();
        }
    }

    @Override
    protected Boolean doInBackground(String... urls) {

        boolean successful = false;
        URL downloadURL = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            downloadURL = new URL(urls[0]);
            connection = (HttpURLConnection) downloadURL.openConnection();
            contentLength = connection.getContentLength();
            inputStream = connection.getInputStream();
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .getAbsolutePath()+"/abcd.pdf");
            fileOutputStream = new FileOutputStream(file);
            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1){
                Log.d("XXXMainActivity", "0 - "+read);
                fileOutputStream.write(buffer,0,read);
                counter += read;
                publishProgress(counter);
            }
            successful = true;
        } catch (MalformedURLException e) {
            Log.d("XXXMainActivity", "1 - "+e.getMessage());
        } catch (IOException e) {
            Log.d("XXXMainActivity", "2 - "+e.getMessage());
        }
        finally {
            if(connection != null){
                connection.disconnect();
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {

                }
            }
        }

        return successful;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if(activity == null){
            Log.d("XXXMyTask", "onProgressUpdate: activity is null");
        }else {
            calculatedProgress = (int)(((double)values[0]/contentLength)*100);
            ((MainActivity)activity).updateProgress(calculatedProgress);
        }

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(activity != null) {
            ((MainActivity) activity).hideProgressBarAfterDownloading();
        }
    }
}