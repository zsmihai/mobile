package com.example.mihai.newsapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mihai.newsapplication.R;
import com.example.mihai.newsapplication.domain.Label;
import com.example.mihai.newsapplication.domain.NewsObject;
import com.example.mihai.newsapplication.domain.User;
import com.example.mihai.newsapplication.network.DTOs.LabelDTO;
import com.example.mihai.newsapplication.network.DTOs.NewsDTO;
import com.example.mihai.newsapplication.network.clients.NewsClient;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Mihai on 15-Jan-18.
 */

public class AddNewsActivity extends AppCompatActivity {
    private CompositeDisposable disposables = new CompositeDisposable();


    private Realm realm;
    private NewsClient newsClient;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnews);

        this.realm = Realm.getDefaultInstance();
        this.newsClient = new NewsClient(this);
        realm.executeTransaction(realm -> this.user = realm.where(User.class).findFirst());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
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

    public void submit(View view) {

        Date date = new Date();
        String labels = ((EditText) findViewById(R.id.labels)).getText().toString();
        String text = ((EditText) findViewById(R.id.text)).getText().toString();

        String[] split = labels.split(",");
        NewsDTO newsObject = new NewsDTO(user.getUsername(), text, date);
        for (String label : split)
        {
            newsObject.getLabels().add(new LabelDTO(label));
        }
        realm.executeTransaction(realm->{
            disposables.add(newsClient.addNews(newsObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::handleAddNews,
                            this::handleError
                    )
            );


        });
    }

    private void handleError(Throwable throwable) {
        Toast toast = Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void handleAddNews(NewsDTO newsDTO) {
        Toast toast = Toast.makeText(this, "News was added ", Toast.LENGTH_SHORT);
        toast.show();
    }
}
