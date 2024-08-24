package com.example.todo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import dagger.hilt.android.HiltAndroidApp

/*Think of it like a VIP pass for notifications. This special channel helps organize and manage notifications,
 making sure they’re delivered in a way that’s just right for you.*/

@HiltAndroidApp
class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(          // notification channel
            "notification_channel_id",
            "notification_name",
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
    }
}