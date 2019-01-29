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

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.models.Playlist;

public class GroupMemberHomeActivity extends AppCompatActivity {

    private Group retrievedGroup;
    private Playlist retrievedPlaylist;
    private FireBase retrievedFireBase;

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
            retrievedPlaylist = (Playlist) intent.getSerializableExtra("playlist");

            Toast.makeText(this, retrievedGroup.getGroupId(), Toast.LENGTH_LONG).show();
        }
        retrievedFireBase = FireBase.getInstance(false, retrievedGroup.getLoginCode());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    /*
     * Inflate options in toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    /*
     * Extract the user input of the toolbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_settings:
                Intent intentSettings = new Intent(GroupMemberHomeActivity.this,
                        SettingsActivity.class);
                intentSettings.putExtra("group", retrievedGroup);
                startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
