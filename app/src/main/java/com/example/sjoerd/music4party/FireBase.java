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
    private FireBase() {

        // Create database
        this.database = FirebaseDatabase.getInstance();

        // Create new group
        DatabaseReference groupRef = database.getReference("groups");
        groupID = groupRef.push().getKey();

        group = new Group(groupID,1234);
        groupRef.child(groupID).setValue(group);
    }

    // Create singleton
    public static FireBase getInstance() {
        if (instance == null) {
            instance = new FireBase();
        }
        return instance;
    }

    public Group getGroup() {
        return group;
    }

}
