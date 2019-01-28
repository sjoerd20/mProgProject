/*
  This file handles the FireBase realtime database

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.models.Playlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// This class is a singleton
public class FireBase {

    private static FireBase instance;
    private String groupId;
    private Playlist playlist;
    private FirebaseDatabase database;
    private Group group = null;

    /*
     * Creates a new group & playlist entry in the database, if the user is a group creator.
     * If user is a group member, checks if the login code is right.
     */
    private FireBase(boolean isCreator, int loginCode) {

        // Create database
        this.database = FirebaseDatabase.getInstance();

        // If creator, create database entry for thr group
        if (isCreator) {

            // Create new group and add to Firebase (g stands for group
            DatabaseReference groupRef = database.getReference("g");
            groupId = Integer.toString(loginCode);
            group = new Group(loginCode, groupId);
            groupRef.child(groupId).setValue(group);

            // Add new playlist to database for group (p stands for playlist)
            DatabaseReference playlistRef = database.getReference("p");
            playlist = Playlist.getInstance(null);
            playlistRef.child(groupId).setValue(playlist);
        }

        // If not creator check login code and return group
        else {
            // TODO check loginCode and join group
            final DatabaseReference groupRef = database.getReference("g");
            final int user_login = loginCode;
            groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Group retrievedGroup = child.getValue(Group.class);
                        if (retrievedGroup.getLoginCode() == user_login) {
                            group = new Group(user_login, retrievedGroup.getGroupId());
                            playlist = Playlist.getInstance(null);
                            return;
                        }
                    }

                    // If not returned, set group to null
                    group = null;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    // Make singleton of class
    public static FireBase getInstance(boolean isCreator, int key) {
        if (instance == null) {
            instance = new FireBase(isCreator, key);
        }
        return instance;
    }

    // Getters
    public Group getGroup() {
        return group;
    }
    public Playlist getPlaylist() {
        return playlist;
    }
}
