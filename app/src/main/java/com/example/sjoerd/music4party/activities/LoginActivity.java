/*
  Login activity. In this activity the user can create a new session as a group creator or join
  a existent group through a login code

  @author      Sjoerd Terpstra

 */

package com.example.sjoerd.music4party.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sjoerd.music4party.FireBase;
import com.example.sjoerd.music4party.models.Group;
import com.example.sjoerd.music4party.R;
import com.example.sjoerd.music4party.models.Playlist;

public class LoginActivity extends AppCompatActivity {

    private int loginCode;
    private Group group;
    private Playlist playlist;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.context = getApplicationContext();
        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Add listener to buttons
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new LoginButtonClickListener());
        Button loginCreateButton = findViewById(R.id.loginCreateButton);
        loginCreateButton.setOnClickListener(new LoginCreateButtonClickListener());
    }

    /*
     * Joins a existing session as a group member. Firebase, Group and Playlist are instantiated and
     * passed to GroupMemberHomeActivity
     */
    private class LoginButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // Retrieve group from database
            EditText loginCodeText = findViewById(R.id.loginCodeText);
            String loginText = loginCodeText.getText().toString();
            if (!loginText.equals("")) {
                loginCode = Integer.parseInt(loginCodeText.getText().toString());

                Toast.makeText(context, " " + loginCode, Toast.LENGTH_LONG).show();

                // Create firebase, group & playlist instances
                FireBase fireBase = FireBase.getInstance(false, loginCode);
                group = fireBase.getGroup();
                playlist = fireBase.getPlaylist();

                // Check if login is successful. If group is null, login was not successful
                if (group == null) {
                    Toast.makeText(context, "Login not successful, try again!", Toast.LENGTH_SHORT).show();
                }
                else {

                    // Start the GroupMemberHomeActivity
                    Intent intent = new Intent(LoginActivity.this,
                            GroupMemberHomeActivity.class);
                    intent.putExtra("group", group);
                    intent.putExtra("playlist", playlist);
                    startActivity(intent);
                }
            }

            // If nothing is entered, prompt the user to try again
            else {
                Toast.makeText(context, "Input required, try again!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
     * Creates a new session as group creator. Firebase, Group and Playlist are instantiated and
     * passed to GroupCreatorHomeActivity
     */
    private class LoginCreateButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FireBase fireBase = FireBase.getInstance(true, 1234);
            group = fireBase.getGroup();
            playlist = fireBase.getPlaylist();

            // Start the GroupCreatorHomeActivity
            Intent intent = new Intent(LoginActivity.this,
                                        GroupCreatorHomeActivity.class);
            intent.putExtra("group", group);
            intent.putExtra("playlist", playlist);
            startActivity(intent);
        }
    }
}
