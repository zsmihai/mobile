package com.example.mihai.newsapplication.activities;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private BarChart barChart;
    private Realm realm;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);

        this.realm = Realm.getDefaultInstance();
        this.newsClient = new NewsClient(this);

        this.listView = findViewById(R.id.newsListView);
        this.barChart = findViewById(R.id.barchart);

        this.localNewsList = new ArrayList<>();

        realm.executeTransaction(realm -> this.user = realm.where(User.class).findFirst());

        getNewsFromRemote();


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
        localNewsList.clear();
        realm.executeTransaction(realm->
        {
            localNewsList.addAll(realm.where(NewsObject.class).findAll());
        });
        populateListView(localNewsList);
        buildChart();
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
                    labels.add(new Label( labelDTO.getLabel()));
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
        buildChart();
    }



    private void buildChart()
    {

        View barchartView = findViewById(R.id.barchartview);
        barchartView.setVisibility(View.GONE);

        barchartView.setAlpha(0f);
        barchartView.setVisibility(View.VISIBLE);

        Map<String, Integer> labelCounter = new HashMap<>();

        for (NewsObject newsObject : localNewsList)
        {
            for (Label label: newsObject.getLabels())
            {
                String labelString = label.getLabel();
                int count = labelCounter.containsKey(labelString) ? labelCounter.get(labelString) : 0;
                labelCounter.put(labelString, count + 1);
            }
        }

        List<BarEntry> barEntries = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        int barIndex = 0;
        for (String label :labelCounter.keySet())
        {
            labels.add(label);
            barEntries.add(new BarEntry(labelCounter.get(label), barIndex));
            barIndex += 1;
        }
        Log.d(TAG, "Chart with "+ barEntries.size() + " entries");
        ArrayList<IBarDataSet> datasets = new ArrayList<>();
        BarDataSet set = new BarDataSet(barEntries, "BarDataSet");
        datasets.add(set);
        BarData data = new BarData(labels, datasets);
        data.setValueTextSize(10f);
        data.setGroupSpace(0.9f);
        barChart.setData(data);

        barChart.invalidate(); // refresh

        barchartView.animate()
                .alpha(1f)
                .setDuration(10000)
                .setListener(null);
    }
}
