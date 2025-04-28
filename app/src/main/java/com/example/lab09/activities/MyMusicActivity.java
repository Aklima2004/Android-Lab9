package com.example.lab09.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09.R;
import com.example.lab09.adapters.SongAdapter;
import com.example.lab09.model.Song;
import com.example.lab09.utils.FileHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MyMusicActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymusic);

        recyclerView = findViewById(R.id.recyclerViewMyMusic);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        List<Song> songList = FileHelper.readSongs(this);
        songAdapter = new SongAdapter(songList, false); // !!! Важно false
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(songAdapter);

        bottomNavigationView.setSelectedItemId(R.id.nav_mymusic);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(MyMusicActivity.this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_mymusic) {
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(MyMusicActivity.this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
