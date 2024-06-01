package org.bubenheimer

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.CATEGORY_SERVICE
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
import org.bubenheimer.R.drawable.ic_stat_name
import org.bubenheimer.R.id.notification_id_service
import org.bubenheimer.R.string.channelid

class ForegroundService : Service() {
    override fun onCreate() {
        Log.d("ForegroundService", "onCreate()")

        super.onCreate()

        val notification: Notification =
            NotificationCompat.Builder(this, getString(channelid))
                .setContentTitle("Foreground service in progress")
                .setCategory(CATEGORY_SERVICE)
                .setOngoing(true)
                .setSmallIcon(ic_stat_name)
                .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)
                .build()

        startForeground(notification_id_service, notification)

        Thread {
            while (true) {
                Log.v("ForegroundService", "Active")
                Thread.sleep(5_000L)
            }
        }.start()
    }

    override fun onDestroy() {
        Log.d("ForegroundService", "onDestroy()")

        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
