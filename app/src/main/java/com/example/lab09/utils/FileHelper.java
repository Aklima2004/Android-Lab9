package com.example.lab09.utils;

import android.content.Context;

import com.example.lab09.model.Song;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private static final String FILE_NAME = "songs.txt";

    // Чтение песен из файла
    public static List<Song> readSongs(Context context) {
        List<Song> songs = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(FILE_NAME)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    songs.add(new Song(parts[0], parts[1]));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            createInitialSongs(context);
            return readSongs(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songs;
    }

    // Добавление песни в файл
    public static void addSong(Context context, Song song) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(FILE_NAME, Context.MODE_APPEND)));
            writer.write(song.getTitle() + ";" + song.getArtist() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Записываем начальные песни
    private static void createInitialSongs(Context context) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)));
            writer.write("Shape of You;Ed Sheeran\n");
            writer.write("Blinding Lights;The Weeknd\n");
            writer.write("Dance Monkey;Tones and I\n");
            writer.write("Someone You Loved;Lewis Capaldi\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
