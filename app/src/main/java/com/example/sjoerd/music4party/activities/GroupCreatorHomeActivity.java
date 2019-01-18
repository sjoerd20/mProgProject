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
import com.example.sjoerd.music4party.fragments.YoutubePlayerFragment;

public class GroupCreatorHomeActivity extends AppCompatActivity {

    private static final String TAG = GroupCreatorHomeActivity.class.getSimpleName();
    private YoutubePlayerFragment youTubePlayerFragment;
    private Group retrievedGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator_home);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Receive intent
        Intent intent = getIntent();
        if (retrievedGroup == null) {
            retrievedGroup = (Group) intent.getSerializableExtra("group");
            Toast.makeText(this, retrievedGroup.getGroupID(), Toast.LENGTH_LONG).show();
        }

        // Initiate youtubePlayerFragment
        youTubePlayerFragment = new YoutubePlayerFragment(this, getSupportFragmentManager());
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
                Intent intentSettings = new Intent(GroupCreatorHomeActivity.this,
                                                    SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            case R.id.action_search:
                Intent intentSearch = new Intent(GroupCreatorHomeActivity.this,
                                                    SearchActivity.class);
                startActivity(intentSearch);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
