package com.saikat.downloader_v2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NonUITaskFragment extends Fragment {

    MyTask myTask = null;
    private Activity activity;

    public NonUITaskFragment() {
        // Required empty public constructor
    }

    public void beginTask(String... arguments){
        myTask = new MyTask(activity);
        myTask.execute(arguments);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
        if(myTask != null){
            myTask.onAttach(activity);
        }
        Log.d("XXXNonUITaskFragment", "1 - onAttach");
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.activity = activity;
//        if(myTask != null){
//            myTask.onAttach(activity);
//        }
//        Log.d("XXXNonUITaskFragment", "1 - onAttach");
//    }
//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("XXXNonUITaskFragment", "2 - onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("XXXNonUITaskFragment", "3 - onCreateView");
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("XXXNonUITaskFragment", "4 - onActivityCreated");
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("XXXNonUITaskFragment", "5 - onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("XXXNonUITaskFragment", "6 - onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("XXXNonUITaskFragment", "7 - onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("XXXNonUITaskFragment", "8 - onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("XXXNonUITaskFragment", "9 - onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("XXXNonUITaskFragment", "10 - onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("XXXNonUITaskFragment", "11 - onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("XXXNonUITaskFragment", "12 - onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(myTask != null){
            myTask.onDetach();
        }
        Log.d("XXXNonUITaskFragment", "13 - onDetach");
    }
}
