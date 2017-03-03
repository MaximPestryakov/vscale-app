package me.maximpestryakov.vscaleapp.api.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Server extends RealmObject {

    private String status;
    private boolean active;
    @PrimaryKey
    private int ctid;
    private String hostname;
    private RealmList<Key> keys;
    private String location;
    private boolean locked;
    @SerializedName("made_from")
    private String madeFrom;
    private String name;
    @SerializedName("private_address")
    private Address privateAddress;
    @SerializedName("public_address")
    private Address publicAddress;
    @SerializedName("rplan")
    private String plan;

    public Server() {
    }

    public String getName() {
        return name;
    }

    public Os getOs() {
        return Os.valueOf(madeFrom.split("_")[0].toUpperCase());
    }

    public String getMadeFrom() {
        return madeFrom;
    }

    public String getIpAddress() {
        return publicAddress.getAddress();
    }

    public Status getStatus() {
        return Status.valueOf(status.toUpperCase());
    }

    public Plan getPlan() {
        return Plan.valueOf(plan.toUpperCase());
    }

    public int getCtid() {
        return ctid;
    }

    public Location getLocation() {
        return Location.valueOf(location.substring(0, 2).toUpperCase());
    }

    public enum Status {
        STARTED, STOPPED, DELETED, BILLING
    }

    public enum Location {
        MSK, SPB
    }

    public enum Plan {
        SMALL, MEDIUM, LARGE, HUGE, MONSTER
    }

    public enum Os {
        CENTOS, DEBIAN, FEDORA, OPENSUSE, UBUNTU
    }
}
