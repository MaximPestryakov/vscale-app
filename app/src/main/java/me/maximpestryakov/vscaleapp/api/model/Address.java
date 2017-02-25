package me.maximpestryakov.vscaleapp.api.model;

import io.realm.RealmObject;

public class Address extends RealmObject {

    private String address;
    private String gateway;
    private String netmask;

    public Address() {
    }

    public String getAddress() {
        return address;
    }
}
