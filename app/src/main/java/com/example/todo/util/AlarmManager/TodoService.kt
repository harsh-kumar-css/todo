package com.example.todo.util.AlarmManager

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.example.todo.data.local.Todo
import com.example.todo.domain.repository.TodoRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TodoService : IntentService("TodoInsertService") {

    @Inject
    lateinit var todoRepository: TodoRepository

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onHandleIntent(intent: Intent?) {
        val todo = intent?.getSerializableExtra("todo") as? Todo ?: return

        println("service started")
        // Perform the database insertion on a background thread
        CoroutineScope(Dispatchers.IO).launch {
            try {
                todoRepository.insertTodo(todo)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}