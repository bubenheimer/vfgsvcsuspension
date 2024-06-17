package org.bubenheimer

import android.app.Notification
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.CATEGORY_SERVICE
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
import org.bubenheimer.R.drawable.ic_stat_name
import org.bubenheimer.R.id.notification_id_service
import org.bubenheimer.R.string.channelid

class StartedService : Service() {
    override fun onCreate() {
        Log.d("StartedService", "onCreate()")

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
    }

    override fun onDestroy() {
        Log.d("StartedService", "onDestroy()")

        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        applicationContext.bindService(
            Intent(applicationContext, BoundService::class.java),
            object : ServiceConnection {
                override fun onServiceConnected(
                    componentName: ComponentName,
                    iBinder: IBinder
                ) {}

                override fun onServiceDisconnected(componentName: ComponentName) {}
            },
            BIND_ABOVE_CLIENT or BIND_AUTO_CREATE
        )

        Thread {
            while (true) {
                Log.v("StartedService", "Active")
                Thread.sleep(5_000L)
            }
        }.start()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?) = null
}
