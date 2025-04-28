package com.example.lab09.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09.R;
import com.example.lab09.adapters.SongAdapter;
import com.example.lab09.model.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerViewSongs);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setupHomeSongs(); // Загружаем песни

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_mymusic) {
                startActivity(new Intent(HomeActivity.this, MyMusicActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    private void setupHomeSongs() {
        List<Song> homeSongs = new ArrayList<>();
        homeSongs.add(new Song("Lose Yourself", "Eminem"));
        homeSongs.add(new Song("Bad Guy", "Billie Eilish"));
        homeSongs.add(new Song("Levitating", "Dua Lipa"));
        homeSongs.add(new Song("Stay", "The Kid LAROI & Justin Bieber"));
        homeSongs.add(new Song("Blinding Lights", "The Weeknd"));

        songAdapter = new SongAdapter(homeSongs, true); // !!! Важно true
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(songAdapter);
    }
}
