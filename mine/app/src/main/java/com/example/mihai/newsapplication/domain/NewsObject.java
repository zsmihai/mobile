package com.example.mihai.newsapplication.domain;

import java.util.Date;
import java.util.Set;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class NewsObject extends RealmObject {

    @PrimaryKey
    private Integer id;

    @Required
    private String author;

    @Required
    private String text;

    @Required
    private Date publishDate;

    @Required
    private RealmList<Label> labels;

    public NewsObject(Integer id, String author, String text, Date publishDate) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.publishDate = publishDate;
    }

    public NewsObject() {
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

    public void setLabels(RealmList<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return author + " " + publishDate + ": " + text.substring(0, Math.min(32, text.length()));
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public RealmList<Label> getLabels() {
        return labels;
    }
}
