package me.maximpestryakov.vscaleapp.api.model;

import com.google.gson.annotations.SerializedName;

public class Account {

    Info info;

    public static class Info {
        private String actdate;

        private String country;

        private String email;

        @SerializedName("face_id")
        private String faceId;

        private String id;

        @SerializedName("is_blocked")
        private boolean blocked;

        private String locale;

        private String middlename;

        private String mobile;

        private String name;

        private String state;

        private String surname;

    }

    public String getName() {
        return info.name;
    }
}
