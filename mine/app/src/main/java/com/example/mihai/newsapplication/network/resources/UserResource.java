package com.example.mihai.newsapplication.network.resources;

import com.example.mihai.newsapplication.network.DTOs.TokenDTO;
import com.example.mihai.newsapplication.network.DTOs.UserDTO;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mihai on 13-Jan-18.
 */

public interface UserResource {
    @POST("/login")
    Observable<TokenDTO> login(@Body UserDTO user);
}
