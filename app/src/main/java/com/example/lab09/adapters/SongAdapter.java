package com.example.lab09.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab09.R;
import com.example.lab09.model.Song;
import com.example.lab09.service.MusicService;
import com.example.lab09.utils.FileHelper;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;
    private Context context;
    private boolean showButtons;

    public SongAdapter(List<Song> songList, boolean showButtons) {
        this.songList = songList;
        this.showButtons = showButtons;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.textViewTitle.setText(song.getTitle());
        holder.textViewArtist.setText(song.getArtist());

        if (showButtons) {
            holder.buttonPlay.setVisibility(View.VISIBLE);
            holder.buttonStop.setVisibility(View.VISIBLE);
            holder.buttonAdd.setVisibility(View.VISIBLE);

            holder.buttonAdd.setOnClickListener(v -> {
                FileHelper.addSong(context, song);
                Toast.makeText(context, "Песня добавлена в файл", Toast.LENGTH_SHORT).show();
            });

            holder.buttonPlay.setOnClickListener(v -> {
                Intent intent = new Intent(context, MusicService.class);
                context.startService(intent);
                Toast.makeText(context, "Музыка запущена", Toast.LENGTH_SHORT).show();
            });

            holder.buttonStop.setOnClickListener(v -> {
                Intent intent = new Intent(context, MusicService.class);
                context.stopService(intent);
                Toast.makeText(context, "Музыка остановлена", Toast.LENGTH_SHORT).show();
            });

        } else {
            holder.buttonPlay.setVisibility(View.GONE);
            holder.buttonStop.setVisibility(View.GONE);
            holder.buttonAdd.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewArtist;
        ImageView buttonPlay, buttonStop, buttonAdd;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewArtist = itemView.findViewById(R.id.textViewArtist);
            buttonPlay = itemView.findViewById(R.id.buttonPlay);
            buttonStop = itemView.findViewById(R.id.buttonStop);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }
    }
}
