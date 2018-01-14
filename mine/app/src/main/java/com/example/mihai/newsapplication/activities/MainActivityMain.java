package com.example.mihai.newsapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mihai.newsapplication.R;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class MainActivityMain extends AppCompatActivity {
    private static final String TAG = MainActivityMain.class.getSimpleName();
    public MainActivityMain() {
        Log.d(TAG, "The constructor event");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "The onCreate() event");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "The onStart() event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "The onRestart() event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "The onStop() event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void handleWriteNewsBtn(View view) {
    }

    public void handleAllNewsBtn(View view) {
    }
}
