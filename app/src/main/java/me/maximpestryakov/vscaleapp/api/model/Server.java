package me.maximpestryakov.vscaleapp.api.model;

import com.google.gson.annotations.SerializedName;

public class Server {

    private boolean active;

    private int ctid;

    private String hostname;

    private SshKey keys;

    private String location;

    private boolean locked;

    private String made_from;

    @SerializedName("private_address")
    private Address privateAddress;

    @SerializedName("public_address")
    private Address publicAddress;

    private String rplan;

    String status;

    public class SshKey {

        private String name;

        private int id;
    }

    public class Address {

        private String address;

        private String gateway;

        private String netmask;
    }
}
