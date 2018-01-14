package com.example.mihai.newsapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.mihai.newsapplication.R;
import com.example.mihai.newsapplication.domain.NewsObject;
import com.example.mihai.newsapplication.domain.User;
import com.example.mihai.newsapplication.domain.Label;
import com.example.mihai.newsapplication.network.DTOs.LabelDTO;
import com.example.mihai.newsapplication.network.DTOs.NewsDTO;
import com.example.mihai.newsapplication.network.clients.NewsClient;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class NewsListActivity extends AppCompatActivity{

    private static final String TAG = NewsListActivity.class.getSimpleName();
    private NewsClient newsClient;

    private CompositeDisposable disposables = new CompositeDisposable();
    private ListView listView;
    private List<NewsObject> localNewsList;
    private AutoCompleteTextView autoCompleteTextView;

    private PieChart pieChart;
    private Realm realm;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);

        this.realm = Realm.getDefaultInstance();
        this. newsClient = new NewsClient(this);

        this.listView = findViewById(R.id.newsListView);
        //this.autoCompleteTextView = findViewById(R.id.authorNameTextField);
        //this.pieChart = findViewById(R.id.piechart);
        //this.pieChart.setUsePercentValues(true);

        this.localNewsList = new ArrayList<>();

        realm.executeTransaction(realm -> this.user = realm.where(User.class).findFirst());

        getNewsFromRemote();

        Log.d(TAG,user.getUsername());



        /*if (!myBooksOnly){
            realm.executeTransaction(realm -> {
                solveView(realm.where(Book.class).findAll());
            });
        }else{
            solveView(user.getBooks());
        }
/*
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Book book = (Book) adapterView.getItemAtPosition(i);

            Intent intent = new Intent(this, SingleBookActivity.class);
            intent.putExtra("bookId", book.getId());
            startActivity(intent);
        });
*/

        Log.d(TAG, "The onCreate() event");
    }

    private void populateListView(List<NewsObject> news) {
        Log.d(TAG, "Populate list_view with " +news.size() + "news");
        ArrayAdapter<NewsObject> adapter1 = new ArrayAdapter<>(this, R.layout.list_view, news);
        this.listView.setAdapter(adapter1);
    }

    private void getNewsFromRemote() {
        Log.d(TAG, "Enter getNewsFromRemote");
        disposables.add(
                newsClient.getAllRemote().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            this::handleGetNewsFromRemote,
                            this::handleErrorGetNewsFromRemote
                        )
                );

    }

    private void handleErrorGetNewsFromRemote(Throwable throwable) {
        Log.d(TAG, "Enter handleGetNewsFromRemote error");
    }

    private void handleGetNewsFromRemote(Collection<NewsDTO> newsDTOS) {
        Log.d(TAG, "Enter handleGetNewsFromRemote");
        Log.d(TAG, "Received " + newsDTOS.size() + "news");
        realm.executeTransaction(realm->{
            realm.where(NewsObject.class).findAll().deleteAllFromRealm();

            for (NewsDTO newsitem : newsDTOS)
            {
                RealmList<Label> labels = new RealmList<>();
                for (LabelDTO labelDTO : newsitem.getLabels())
                {
                    labels.add(new Label(labelDTO.getId(), labelDTO.getLabel()));
                }

                NewsObject newsObject = new NewsObject(
                        newsitem.getId(),
                        newsitem.getAuthor(),
                        newsitem.getText(),
                        newsitem.getPublishDate()
                );
                newsObject.setLabels(labels);
                realm.copyToRealmOrUpdate(newsObject);

            }
        });
        localNewsList.clear();
        realm.executeTransaction(realm->
        {
            localNewsList.addAll(realm.where(NewsObject.class).findAll());
        });
        populateListView(localNewsList);
    }

}
