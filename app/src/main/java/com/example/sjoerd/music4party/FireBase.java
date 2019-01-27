/*
  This file handles the FireBase realtime database

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party;

import android.support.annotation.NonNull;

import com.example.sjoerd.music4party.models.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// This class is a singleton
public class FireBase {

    private static FireBase instance;
    private String groupId;
    private FirebaseDatabase database;
    private Group group;

    // Constructor
    private FireBase(boolean isCreator, int loginCode) {

        // Create database
        this.database = FirebaseDatabase.getInstance();

        if (isCreator) {
            // Create new group
            DatabaseReference groupRef = database.getReference("groups");
            groupId = Integer.toString(loginCode);
            group = new Group(loginCode, groupId);
            groupRef.child(groupId).setValue(group);
        }

        // If not creator check login code and return group
        else {
            // TODO check loginCode and join group
            final DatabaseReference groupRef = database.getReference("groups");
            final int user_login = loginCode;
            groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Group retrievedGroup = child.getValue(Group.class);
                        if (retrievedGroup.getLoginCode() == user_login) {
                            group = new Group(user_login, retrievedGroup.getGroupId());
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

    // Make singleton
    public static FireBase getInstance(boolean isCreator, int key) {
        if (instance == null) {
            instance = new FireBase(isCreator, key);
        }
        return instance;
    }

    public Group getGroup() {
        return group;
    }
}
