package com.example.mihai.newsapplication.network.DTOs;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Set;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class NewsDTO {

    @SerializedName("id")
    private Integer id;

    @SerializedName("author")
    private String author;

    @SerializedName("text")
    private String text;

    @SerializedName("publishDate")
    private Date publishDate;

    @SerializedName("labels")
    private Set<LabelDTO> labels;

    public NewsDTO(Integer id, String author, String text, Set<LabelDTO> labels, Date publishDate) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.labels = labels;
        this.publishDate = publishDate;
    }

    public NewsDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<LabelDTO> getLabels() {
        return labels;
    }

    public void setLabels(Set<LabelDTO> labels) {
        this.labels = labels;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
