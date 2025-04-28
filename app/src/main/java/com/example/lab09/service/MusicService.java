package com.example.lab09.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.lab09.R;

public class MusicService extends Service {

    private static final String CHANNEL_ID = "MusicServiceChannel";
    private static final int NOTIFICATION_ID = 1;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Music Playback",
                    NotificationManager.IMPORTANCE_HIGH
            );
            serviceChannel.setDescription("Playing your favorite music");
            serviceChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            serviceChannel.setShowBadge(false);
            serviceChannel.setSound(null, null);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
            mediaPlayer.setLooping(true);
        }
        mediaPlayer.start();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Playback")
                .setContentText("Playing your favorite music")
                .setSmallIcon(R.drawable.ic_music_note)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
                .build();

        // Просто запускаем Foreground Service без указания типа!
        startForeground(NOTIFICATION_ID, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
