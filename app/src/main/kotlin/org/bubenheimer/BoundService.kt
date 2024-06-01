package org.bubenheimer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.util.Log

class BoundService : Service() {
    private val binder = Binder()

    override fun onCreate() {
        Log.d("BoundService", "onCreate()")

        super.onCreate()
    }

    override fun onDestroy() {
        Log.d("BoundService", "onDestroy()")

        super.onDestroy()
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ) = throw UnsupportedOperationException()

    override fun onBind(intent: Intent?) = binder
}
