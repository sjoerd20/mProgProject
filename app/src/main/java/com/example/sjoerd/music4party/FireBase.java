package com.example.sjoerd.music4party;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

// This class is a singleton
public class FireBase {

    private static FireBase instance;

    private FirebaseDatabase database;

    // Constructor
    private FireBase() {
        this.database = FirebaseDatabase.getInstance();
    }

    // Create singleton
    public static FireBase getInstance() {
        if (instance == null) {
            instance = new FireBase();
        }
        return instance;
    }
}
