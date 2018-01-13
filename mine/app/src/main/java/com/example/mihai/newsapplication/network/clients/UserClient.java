package com.example.mihai.newsapplication.network.clients;

import android.content.Context;

import com.example.mihai.newsapplication.R;
import com.example.mihai.newsapplication.network.DTOs.TokenDTO;
import com.example.mihai.newsapplication.network.DTOs.UserDTO;
import com.example.mihai.newsapplication.network.resources.UserResource;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mihai on 13-Jan-18.
 */

public class UserClient{
    private UserResource userResource;
    private Context context;

    public UserClient(Context context) {
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.server_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.userResource = retrofit.create(UserResource.class);
    }

    public Observable<TokenDTO> login(UserDTO user){
        return userResource.login(user);
    }
}
