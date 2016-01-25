package com.cs.mju.hanmadang.Function.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.app.ActionBar;

import com.cs.mju.hanmadang.R;

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();

        addPreferencesFromResource(R.xml.pref_notification);
    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
