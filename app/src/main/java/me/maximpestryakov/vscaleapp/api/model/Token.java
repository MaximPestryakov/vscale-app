package me.maximpestryakov.vscaleapp.api.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Token extends RealmObject {

    @PrimaryKey
    private String name;
    private String value;

    public Token() {
    }

    public Token(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
