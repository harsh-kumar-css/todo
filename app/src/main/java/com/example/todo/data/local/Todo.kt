package com.example.todo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Todo(
    val title: String,
    val description : String?,
    val isDone : Boolean,
    val time : Long,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
) : Serializable