/*
  Home activity for a group member. This activity contains the name of the current youtube video,
  a list with the next videos and an search option to find new videos

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.YoutubeSearchRequest;
import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.models.Playlist;
import com.example.sjoerd.music4party.models.Video;

import java.util.ArrayList;

public class GroupMemberHomeActivity extends AppCompatActivity {

    private Group retrievedGroup;
    private Playlist retrievedPlaylist;
    private FireBase retrievedFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member_home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

//    /*
//     * Search videos with the Youtube Data API using a keyword input from the user
//     */
//    public void onSearchClicked(View view) {
//        EditText searchTextView = findViewById(R.id.creatorSearchText);
//        String searchText = searchTextView.getText().toString();
//        if (!searchText.equals("")) {
//            YoutubeSearchRequest youtubeSearchRequest = new YoutubeSearchRequest(this);
//            youtubeSearchRequest.getVideos(this, searchText);
//        }
//    }
//
//    /*
//     * If successful found a video, play it
//     */
//    @Override
//    public void gotVideos(ArrayList<Video> videos) {
//        try {
//            // Update playlist
//            retrievedPlaylist.removeVideo();
//            retrievedPlaylist.addVideo(videos.get(0));
//            retrievedFireBase.changePlaylist(retrievedPlaylist);
//        }
//        catch(IndexOutOfBoundsException e) {
//            Toast.makeText(this, "No matching videos found", Toast.LENGTH_LONG).show();
//        }
//        Toast.makeText(this, "done!", Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void gotVideosError(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }
}
