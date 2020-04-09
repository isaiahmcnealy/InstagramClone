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
import com.parse.SignUpCallback;

/**
 * A login screen that offers login via username/password.
 */

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null){
            goToMainActivity();
            Toast.makeText(LoginActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();

        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClick login button pressed");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Register button clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                registerUser(username, password);
            }
        });



    }

    private void registerUser(String username, String password) {
        Log.i(TAG, "Attempting to RegisterUser" );
        // create ParseUser
        ParseUser user = new ParseUser();
        //Set Core Properties
        user.setUsername(username);
        user.setPassword(password);
        // Set custom properties
        // user.put("key", "value");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error registering new user!", e); // TODO: improve error handling
                    Toast.makeText(LoginActivity.this, "Issue with registering user", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // navigate to the main activity if the user has signed in properly
                    goToMainActivity();
                    Toast.makeText(LoginActivity.this, "Signup Sucess", Toast.LENGTH_SHORT).show();
                }
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
                // navigate to the main activity if the user has signed in properly
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
