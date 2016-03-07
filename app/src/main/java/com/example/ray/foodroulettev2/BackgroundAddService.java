package com.example.ray.foodroulettev2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by raylee on 3/7/16.
 */
public class BackgroundAddService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    int length;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.addsong);
        player.setLooping(true); // Set looping
        player.setVolume(50, 50);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        Log.v("FSDF", "Initializing sounds...");
        return 1;
    }

    public void onStart(Intent intent, int startId) {
        // TODO

    }
    public IBinder onUnBind(Intent arg0) {
        // TODO Auto-generated method stub

        return null;
    }

    public void onStop() {
        // player.stop();

    }
    public void onPause() {
    }

    @Override
    public void onDestroy() {

        Log.v("FSDF", "Destroying" +
                " sounds...");
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}