package src.com.services;

import src.com.domain.Label;
import src.com.domain.NewsObject;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewsDTO implements Serializable {

    private Integer id;


    private String author;


    private String text;


    private Set<Label> labels;


    private Date publishDate;

    public NewsDTO(Integer id, String author, String text, Set<Label> labels, Date publishDate) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.labels = labels;
        this.publishDate = publishDate;
    }

    public NewsDTO(NewsObject newsObject)
    {
        this.id = newsObject.getId();
        this.author = newsObject.getAuthor();
        this.text = newsObject.getText();
        this.labels = new HashSet<>(newsObject.getLabels());
        this.publishDate = newsObject.getPublishDate();
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

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
