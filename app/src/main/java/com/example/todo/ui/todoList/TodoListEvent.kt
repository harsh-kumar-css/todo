package com.example.todo.ui.todoList

import com.example.todo.data.local.Todo

sealed class TodoListEvent {
        data class OnTodoClick(val todo : Todo) : TodoListEvent()
        data class OnDoneClick(val todo : Todo, val isDone : Boolean) : TodoListEvent()
        data class OnDeleteClick(val todo : Todo) : TodoListEvent()
        object OnAddTodoClick : TodoListEvent()
        object OnUndoTodoClick : TodoListEvent()
}