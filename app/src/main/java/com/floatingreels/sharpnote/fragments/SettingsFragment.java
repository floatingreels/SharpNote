package com.floatingreels.sharpnote.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.preference.PreferenceFragmentCompat;

import com.floatingreels.sharpnote.R;

public class SettingsFragment extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}