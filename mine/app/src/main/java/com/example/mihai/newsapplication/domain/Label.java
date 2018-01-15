package com.example.mihai.newsapplication.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Mihai on 14-Jan-18.
 */

public class Label extends RealmObject{

    @PrimaryKey
    private String label;

    public Label( String label) {

        this.label = label;
    }

    public Label() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
