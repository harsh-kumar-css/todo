package com.example.todo.util.AlarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.todo.data.local.Todo

class AlarmSchedulerImpl(
    private val context : Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    lateinit var intent : Intent

    override fun schedule(todo: Todo) {
         intent = Intent(context,AlarmReceiver::class.java).apply{
            putExtra("todo",todo)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            todo.time,
            PendingIntent.getBroadcast(
                context,
                todo.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}