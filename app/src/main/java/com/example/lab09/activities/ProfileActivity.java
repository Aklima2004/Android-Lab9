package com.example.lab09.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab09.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    private Button buttonLogout;
    private BottomNavigationView bottomNavigationView;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonLogout = findViewById(R.id.buttonLogout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME, "User");

        textViewWelcome.setText("Welcome, " + username + "!");

        buttonLogout.setOnClickListener(v -> showLogoutDialog());

        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_mymusic) {
                startActivity(new Intent(ProfileActivity.this, MyMusicActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                return true;
            }
            return false;
        });
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to log out of your account?")
                .setPositiveButton("Да", (dialog, which) -> {
                    clearUserData();
                    navigateToLogin();
                })
                .setNegativeButton("Нет", null)
                .show();
    }

    private void clearUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
