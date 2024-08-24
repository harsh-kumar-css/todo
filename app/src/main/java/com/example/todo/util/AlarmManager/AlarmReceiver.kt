package com.example.todo.util.AlarmManager

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.todo.R
import com.example.todo.data.local.Todo
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.util.NotificationManager.NotificationHandler
import javax.inject.Inject

class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var todoRepository: TodoRepository

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context?, intent: Intent?) {
        println("Alarm about to trigger")
        val todo = intent?.getSerializableExtra("todo") as? Todo ?: return
        println("Alarm about to trigger  : $todo")
        Toast.makeText(context,"toast",Toast.LENGTH_SHORT).show()
        // Create an Intent to start the TodoService
        val serviceIntent = Intent(context, TodoService::class.java).apply {
            putExtra("todo", todo)
        }

        // Start the service
        context?.startService(serviceIntent)
        val notificationHandler = NotificationHandler(context!!)
        notificationHandler.showNotification(todo)
    }
}