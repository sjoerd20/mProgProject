package com.example.sjoerd.music4party.models;

import com.example.sjoerd.music4party.FireBase;

import java.util.ArrayList;

public class Playlist {

    private static Playlist instance;

    private ArrayList<Video> playlist;

    // Constructor
    private Playlist(ArrayList<Video> playlist) {
        this.playlist = playlist;
    }

    // Make singleton
    public static Playlist getInstance(ArrayList<Video> playlist) {
        if (instance == null) {
            instance = new Playlist(playlist);
        }
        return instance;
    }

    // Getters and setters
    public ArrayList<Video> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Video> playlist) {
        this.playlist = playlist;
    }

    // Add video to playlist
    public void addVideo(Video video) {
        this.playlist.add(video);
    }

    // Remove video from playlist
    public void removeVideo(Video video) {
        this.playlist.remove(0);
    }
}
