package com.example.sjoerd.music4party.models;

import com.example.sjoerd.music4party.FireBase;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {

    private static Playlist instance;

    private ArrayList<Video> playlist = new ArrayList<>();

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
        if (playlist != null) {
            playlist.add(video);
        }
    }

    // Remove video from playlist
    public void removeVideo() {
        if (playlist != null) {
            if (playlist.size() != 0) {
                this.playlist.remove(0);
            }
        }
    }

    // Retrieve video from playlist and remove it
    public Video retrieveVideo() {
        Video nextVideo;
        if (playlist != null) {
            if (playlist.size() != 0) {
                nextVideo = playlist.get(0);
                removeVideo();
                return nextVideo;
            }
        }
        return null;
    }
}
