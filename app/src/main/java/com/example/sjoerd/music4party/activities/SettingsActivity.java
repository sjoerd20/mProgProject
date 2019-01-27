/*
  Settings activity. In this activity certain app settings can be changed to fit the users'
  personal preferences

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.models.Group;

public class SettingsActivity extends AppCompatActivity {

    private Group retrievedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Receive intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (retrievedGroup == null) {
                retrievedGroup = (Group) intent.getSerializableExtra("group");
            }
        }

        // Set fields
        TextView loginCodeCode = findViewById(R.id.loginCodeCode);
        loginCodeCode.setText(String.valueOf(retrievedGroup.getLoginCode()));
    }

    // inflate options in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }
}
