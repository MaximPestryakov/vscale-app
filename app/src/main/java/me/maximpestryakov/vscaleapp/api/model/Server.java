package me.maximpestryakov.vscaleapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Server {

    private boolean active;

    private int ctid;

    private String hostname;

    private List<Key> keys;

    private String location;

    private boolean locked;

    private String made_from;

    @SerializedName("private_address")
    private Address privateAddress;

    @SerializedName("public_address")
    private Address publicAddress;

    private String rplan;

    String status;

    public String getHostname() {
        return hostname;
    }

    public class Key {

        private String name;

        private int id;
    }

    public class Address {

        private String address;

        private String gateway;

        private String netmask;
    }
}
