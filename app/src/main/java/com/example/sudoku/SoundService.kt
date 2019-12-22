package com.example.sudoku

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class SoundService : Service() {
    var player: MediaPlayer? = null

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.music)
        player?.isLooping = true
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player?.start()
        return START_STICKY
    }

    override fun onDestroy() {
        player?.stop()
        player?.release()
    }

}
