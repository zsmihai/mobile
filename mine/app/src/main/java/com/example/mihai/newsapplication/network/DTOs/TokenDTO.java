package com.example.mihai.newsapplication.network.DTOs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mihai on 13-Jan-18.
 */

public class TokenDTO {
    @SerializedName("token")
    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }

    public TokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
