package com.example.sjoerd.music4party;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

class YoutubePlayerFragment implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyB91qfzy3kS3ZjtK4YoJ7Wa78afJyY7OHQ ";
//    private ArrayList<String> video_codes = new ArrayList<String>();
    private static final String VIDEO_CODE = "FoCG-WNsZio";
    private static final String VIDEO_CODE2 = "FoCG-WNsZio";

    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private Activity activity;
    private FragmentManager supportFragmentManager;
    protected YouTubePlayer youTubePlayer;

    // Constructor
    public YoutubePlayerFragment(Activity activity, FragmentManager supportFragmentManager) {
        this.activity = activity;
        this.supportFragmentManager = supportFragmentManager;
//        video_codes.add("FoCG-WNsZio");
//        video_codes.add("8ebRM6vSYXw");

        // Initialize youtubePlayer
        youTubePlayerSupportFragment = (YouTubePlayerSupportFragment) supportFragmentManager.
                findFragmentById(R.id.youtube_player_fragment);
        if (youTubePlayerSupportFragment == null) {
            return;
        }

        youTubePlayerSupportFragment.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            this.youTubePlayer = youTubePlayer;

            // Set player to default style
            // CHROMELESS, DEFAULT, MINIMAL
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            youTubePlayer.setShowFullscreenButton(false);

            // Cue video
            youTubePlayer.loadVideo(VIDEO_CODE);
            youTubePlayer.play();

//            // add listener
//            youTubePlayer.setPlaybackEventListener(new VideoPlaybackListener());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.e(TAG, "Youtube Player View initialization failed");
    }

//    public class VideoPlaybackListener implements YouTubePlayer.PlaybackEventListener {
//        @Override
//        public void onPlaying() {
//        }
//
//        @Override
//        public void onPaused() {
//        }
//
//        @Override
//        public void onStopped() {
//            // TODO jump to next video
//            // Cue next video
//            youTubePlayer.loadVideo(VIDEO_CODE2);
//            youTubePlayer.play();
//        }
//
//        @Override
//        public void onBuffering(boolean b) {
//
//        }
//
//        @Override
//        public void onSeekTo(int i) {
//
//        }
//    }
}
