package com.example.todo.util.NotificationManager

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.todo.MainActivity
import com.example.todo.data.local.Todo
import kotlin.random.Random

/*
We’ve successfully set up a notification channel, and now we’re going to build our first notification.
It’s like giving our notification its own identity, starting with something simple — a small icon, a title,
and a short description.
*/

class NotificationHandler(private val context: Context) {

    // this notificationManager is kind of the another application or service
    // which shows our notification , that is why here we are asking the notificationManager to show our notification

    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "notification_channel_id"

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    fun showNotification(todo : Todo) {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("Task Added Bitchhh 🖕🖕🖕!!!")
            .setContentText(todo.title)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            // fires the pending intent when we click on the notification, that eventually fires the intent,
            // PendingIntent.getActivity() tells to start the MainActivity of our app
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Random.nextInt(),notification)
    }
}
//

/* now we want to go to our app, when we click on the notification
 but as we left our app , we wont be able to run our code , as we are not in our app, so for that we have pending intents
 pending intents allows other apps to execute some piece of code from our app*/


/*
 as when we want to launch some activity of our app from another app, but the other app don't have the permission to do so
 here pending intents come in picture
 pending intents are a wrapper around a regular intent that is designed to be used by another application, that gives
 permission to the other app to perform the action in our application with all the permissions that we have granted
 to our application
 */
