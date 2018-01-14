package com.example.mihai.newsapplication.network.clients;

import android.content.Context;

import com.example.mihai.newsapplication.R;
import com.example.mihai.newsapplication.network.DTOs.NewsDTO;
import com.example.mihai.newsapplication.network.resources.NewsResource;
import com.example.mihai.newsapplication.network.resources.UserResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class NewsClient {
    private NewsResource newsResource;
    private Context context;

    public NewsClient(Context context) {
        this.context = context;

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.server_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.newsResource = retrofit.create(NewsResource.class);
    }

    public Observable<Collection<NewsDTO>> getAllRemote()
    {
        return this.newsResource.getAllRemote();
    }

    public Observable<NewsDTO> getById(Integer id) {
        return this.newsResource.getById(id);
    }
}
