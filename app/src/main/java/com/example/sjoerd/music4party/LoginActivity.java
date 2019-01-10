package com.example.sjoerd.music4party;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private int loginCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);

        // Add listener to buttons
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new LoginButtonClickListener());
        Button loginCreateButton = findViewById(R.id.loginCreateButton);
        loginCreateButton.setOnClickListener(new LoginCreateButtonClickListener());
    }

    // Listener for login code OK button
    private class LoginButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO check login code with firebase database and save user to database

            // Start the GroupMemberHomeActivity
            Intent intent = new Intent(LoginActivity.this,
                                        GroupMemberHomeActivity.class);
            startActivity(intent);
        }
    }

    // Listener for create new session button
    private class LoginCreateButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO create firebase database and add user to it

            // Start the GroupCreatorHomeActivity
            Intent intent = new Intent(LoginActivity.this,
                                        GroupCreatorHomeActivity.class);
            startActivity(intent);
        }
    }
}
