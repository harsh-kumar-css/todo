package com.example.todo.domain.repository

import com.example.todo.data.local.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun getTodo(id : Int) : Todo
    fun getTodoList() : Flow<List<Todo>>
    suspend fun deleteTodo(todo : Todo)
    suspend fun insertTodo(todo : Todo)
    suspend fun insertTodos(todos : List<Todo>)
}