package com.example.mihai.newsapplication.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
        Log.d(TAG, "All news button handler");
        startActivity(new Intent(this, AddNewsActivity.class));
    }

    public void handleAllNewsBtn(View view) {
        Log.d(TAG, "All news button handler");
        startActivity(new Intent(this, NewsListActivity.class));
    }

    public void sendSupportMail(View view) {

        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:support@zsiskumail..."));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i(TAG, "Finished sending email...");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivityMain.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
