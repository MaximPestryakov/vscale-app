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

    @SerializedName("made_from")
    private String madeFrom;

    private String name;

    @SerializedName("private_address")
    private Address privateAddress;

    @SerializedName("public_address")
    private Address publicAddress;

    private String rplan;

    String status;

    public String getName() {
        return name;
    }

    public String getOs() {
        return madeFrom.split("_")[0];
    }

    public String getIpAddress() {
        return publicAddress.address;
    }

    public String getStatus() {
        return status;
    }

    public String getRplan() {
        return rplan;
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
