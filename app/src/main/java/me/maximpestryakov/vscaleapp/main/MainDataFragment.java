package me.maximpestryakov.vscaleapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import me.maximpestryakov.vscaleapp.api.model.Server;

public class MainDataFragment extends Fragment {

    private int state = -1;

    private String dialogTitle;
    private String dialogMessage;

    private List<Server> servers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }
}