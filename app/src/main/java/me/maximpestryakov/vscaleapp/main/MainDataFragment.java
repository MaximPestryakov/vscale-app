package me.maximpestryakov.vscaleapp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class MainDataFragment extends Fragment {

    private int state = -1;

    private int dialogTitle;
    private int dialogMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogTitle(int dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public int getDialogMessage() {
        return dialogMessage;
    }

    public void setDialogMessage(int dialogMessage) {
        this.dialogMessage = dialogMessage;
    }
}
