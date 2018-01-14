package com.example.mihai.newsapplication.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class Label extends RealmObject{

    @PrimaryKey
    private Integer id;

    @Required
    private String label;

    public Label(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Label() {
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
