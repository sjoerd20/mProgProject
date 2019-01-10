package com.example.sjoerd.music4party;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);
    }
}
