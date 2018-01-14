package com.example.mihai.newsapplication.network.DTOs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class LabelDTO {

    @SerializedName("id")
    private Integer id;

    @SerializedName("label")
    private String label;

    public LabelDTO(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
