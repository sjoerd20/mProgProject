/*
  Home activity for a group member. This activity contains the name of the current youtube video,
  a list with the next videos and an search option to find new videos

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.R;

public class GroupMemberHomeActivity extends AppCompatActivity {

    private Group retrievedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member_home);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Receive intent
        Intent intent = getIntent();
        if (retrievedGroup == null) {
            retrievedGroup = (Group) intent.getSerializableExtra("group");
            Toast.makeText(this, retrievedGroup.getGroupID(), Toast.LENGTH_LONG).show();
        }
    }

    // inflate options in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    // extract the user input of the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(GroupMemberHomeActivity.this,
                        SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            case R.id.action_search:
                Intent intentSearch = new Intent(GroupMemberHomeActivity.this,
                        SearchActivity.class);
                startActivity(intentSearch);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
