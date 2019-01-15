package com.example.sjoerd.music4party;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// This class is a singleton
public class FireBase {

    private static FireBase instance;
    private String groupID;
    private FirebaseDatabase database;
    private Group group;

    // Constructor
    private FireBase(boolean isCreator) {

        // Create database
        this.database = FirebaseDatabase.getInstance();

        if (isCreator) {
            // Create new group
            DatabaseReference groupRef = database.getReference("groups");
            groupID = groupRef.push().getKey();

            group = new Group(groupID, 1234);
            groupRef.child(groupID).setValue(group);
        }

        // Of not creator check login code and return group
        else {
            // TODO check loginCode and create group
            group = new Group("user_group", 1234);
        }
    }

    // Create singleton
    public static FireBase getInstance(boolean isCreator) {
        if (instance == null) {
            instance = new FireBase(isCreator);
        }
        return instance;
    }

    public Group getGroup() {
        return group;
    }

}
