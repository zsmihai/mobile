package com.example.mihai.newsapplication.network.resources;

import com.example.mihai.newsapplication.network.DTOs.NewsDTO;

import java.util.Collection;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mihai on 14-Jan-18.
 */

public interface NewsResource {

    @GET("/news")
    Observable<Collection<NewsDTO>> getAllRemote();

    @GET("/news/{news_id}")
    Observable<NewsDTO> getById(@Path("news_id") Integer id);
}
