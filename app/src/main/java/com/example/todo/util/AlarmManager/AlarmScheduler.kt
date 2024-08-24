package com.example.todo.util.AlarmManager

import com.example.todo.data.local.Todo

interface AlarmScheduler {
    fun schedule(todo : Todo)
}