package com.example.sjoerd.music4party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class GroupCreatorHomeActivity extends AppCompatActivity {

    private static final String API_KEY = "AIzaSyB91qfzy3kS3ZjtK4YoJ7Wa78afJyY7OHQ ";
    private static final String VIDEO_CODE = "FoCG-WNsZio";
    private static final String TAG = GroupCreatorHomeActivity.class.getSimpleName();
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creator_home);

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

        initializeYoutubePlayer();
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

    // Initialize youtubeplayer
    private void initializeYoutubePlayer() {
        youTubePlayerSupportFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager().
                findFragmentById(R.id.youtube_player_fragment);
        if (youTubePlayerSupportFragment == null) {
            return;
        }
        youTubePlayerSupportFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {
                    // Set player to default style
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    // Cue video
                    youTubePlayer.cueVideo(VIDEO_CODE);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });

    }
}
