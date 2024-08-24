package com.example.todo.data.remote

import com.example.todo.data.local.Todo
import com.example.todo.data.local.TodoDao
import com.example.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao : TodoDao) : TodoRepository {

    override suspend fun getTodo(id: Int): Todo {
        return dao.getTodo(id)
    }

    override fun getTodoList(): Flow<List<Todo>> {
        return dao.getTodoList()
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun insertTodos(todos: List<Todo>) {
       dao.insertTodos(todos)
    }
}