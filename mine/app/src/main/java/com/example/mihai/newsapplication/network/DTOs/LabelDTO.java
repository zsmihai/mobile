package com.example.mihai.newsapplication.network.DTOs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class LabelDTO {


    @SerializedName("label")
    private String label;

    public LabelDTO(String label) {
        this.label = label;
    }






    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
