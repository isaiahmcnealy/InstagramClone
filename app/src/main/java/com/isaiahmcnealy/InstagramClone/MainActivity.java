package com.isaiahmcnealy.InstagramClone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.isaiahmcnealy.InstagramClone.fragments.ComposeFragment;
import com.isaiahmcnealy.InstagramClone.fragments.PostFragment;
import com.isaiahmcnealy.InstagramClone.fragments.ProfileFragment;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();

//        // define your fragments here
//        final Fragment compose = new FirstFragment();
//        final Fragment login = new SecondFragment();
//        final Fragment fragment3 = new ThirdFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new PostFragment();
                        break;
                    case R.id.action_compose:
                        // do something here
                        Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_logout:
                        // do something here
                        Toast.makeText(MainActivity.this, "Logout!", Toast.LENGTH_SHORT).show();
                        signOut();
                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        // do something here
                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }



    private void goToLoginActivity() {
        Log.i(TAG, "Attempting to return to loginActivity");
        // use intent system to navigate to new activity
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();

    }

    private void signOut(){
        Log.i(TAG, "Attempting to log out");
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will not be null
        Toast.makeText(MainActivity.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
        goToLoginActivity();
    }

}
