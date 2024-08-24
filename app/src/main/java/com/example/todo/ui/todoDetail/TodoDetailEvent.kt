package com.example.todo.ui.todoDetail

import com.example.todo.data.local.Todo

sealed class TodoDetailEvent {
    data class OnTitleChange(val title: String) : TodoDetailEvent()
    data class OnDescriptionChange(val description: String) : TodoDetailEvent()
    data class OnDateChange(val time: Long) : TodoDetailEvent()
    object OnSaveTodoClick : TodoDetailEvent()
    data class AddTodoInFuture(val todo : Todo) : TodoDetailEvent()
}
