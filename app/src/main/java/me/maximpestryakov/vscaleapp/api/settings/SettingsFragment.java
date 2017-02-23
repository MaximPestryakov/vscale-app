package me.maximpestryakov.vscaleapp.api.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import me.maximpestryakov.vscaleapp.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
