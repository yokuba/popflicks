package com.example.aliciamaclennan.popflicks;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by amac on 8/30/16.
 */
public class MovieFragment  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public static class MovieDetail extends PreferenceFragment {

    }
}

