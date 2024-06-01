package org.bubenheimer

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

class StartedService : Service() {
    override fun onCreate() {
        Log.d("StartedService", "onCreate()")

        super.onCreate()
    }

    override fun onDestroy() {
        Log.d("StartedService", "onDestroy()")

        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        Thread {
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

            startForegroundService(Intent(this, ForegroundService::class.java))

            stopSelf()
        }.start()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?) = null
}
