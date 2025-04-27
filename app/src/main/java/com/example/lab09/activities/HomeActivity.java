package com.example.lab09.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab09.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    // Уже на главной, ничего не делаем
                    return true;
                case R.id.nav_mymusic:
                    startActivity(new Intent(HomeActivity.this, MyMusicActivity.class));
                    return true;
                case R.id.nav_profile:
                    startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        });
    }
}
