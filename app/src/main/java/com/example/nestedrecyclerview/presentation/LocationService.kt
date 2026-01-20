package com.example.nestedrecyclerview.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class LocationService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    val job = SupervisorJob()

    val firstCoroutineScope = CoroutineScope(Dispatchers.IO + job)

    @OptIn(DelicateCoroutinesApi::class)
    val customCoroutineScope = CoroutineScope(GlobalScope.coroutineContext + Dispatchers.IO)

    fun captureTheLatestLocation() {

        customCoroutineScope.launch {

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        customCoroutineScope.cancel()
    }

}