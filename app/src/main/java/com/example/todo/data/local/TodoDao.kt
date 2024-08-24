package com.example.todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Insert
    suspend fun insertTodos(todoList: List<Todo>)

    @Query("SELECT * from todo where id =:id")
    suspend fun getTodo(id: Int) : Todo

    @Query("SELECT * from todo")
    fun getTodoList() : Flow<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo : Todo)

}