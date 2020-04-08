package com.isaiahmcnealy.InstagramClone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * A login screen that offers login via username/password.
 */

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClick login button pressed");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });


    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to loginUser" );
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null) {
                    // TODO: improve error handling
                    Log.e(TAG, "Issue with login");
                    Toast.makeText(LoginActivity.this, "Issue with logging in", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: navigate to the main activity if the user has signed in properly
                goToMainActivity();
                Toast.makeText(LoginActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // use intent system to navigate to new activity
    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


}
