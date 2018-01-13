package com.example.mihai.newsapplication.network.DTOs;

import com.example.mihai.newsapplication.domain.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mihai on 13-Jan-18.
 */

public class UserDTO {

    @SerializedName("email")
    private String username;

    @SerializedName("password")
    private String password;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO() {
    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
