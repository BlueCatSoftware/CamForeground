package com.ameen.camforeground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.util.concurrent.Executors

class MyService : Service() {

    private var CHANNEL_ID = "ForegroundServiceChannel"
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        var notificationIntent = Intent(this, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        var notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("runing foregroud")
            .setContentText("running")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        runTask()

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun runTask() {
        TODO("Not yet implemented")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            var manager : NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
    }
}