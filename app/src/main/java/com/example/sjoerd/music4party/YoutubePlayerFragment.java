package com.example.sjoerd.music4party;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import static android.content.ContentValues.TAG;

public class YoutubePlayerFragment {

    private static final String API_KEY = "AIzaSyB91qfzy3kS3ZjtK4YoJ7Wa78afJyY7OHQ ";
    private static final String VIDEO_CODE = "FoCG-WNsZio";

    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private Activity activity;
    private FragmentManager supportFragmentManager;

    // Constructor
    public YoutubePlayerFragment(Activity activity, FragmentManager supportFragmentManager) {
        this.activity = activity;
        this.supportFragmentManager = supportFragmentManager;

        // Initialize youtubePlayer
        youTubePlayerSupportFragment = (YouTubePlayerSupportFragment) supportFragmentManager.
                                        findFragmentById(R.id.youtube_player_fragment);
        if (youTubePlayerSupportFragment == null) {
            return;
        }

        youTubePlayerSupportFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {
                    // Set player to default style
                    // CHROMELESS, DEFAULT, MINIMAL
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
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
