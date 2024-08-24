package com.example.todo.ui.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.Todo
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.util.Routes
import com.example.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    val todoList = repository.getTodoList()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun OnEvent(event: TodoListEvent) {
        when(event){
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.TODO_DETAIL))
            }
            is TodoListEvent.OnDeleteClick -> {
               deleteTodo(event.todo)
            }
            is TodoListEvent.OnDoneClick -> {
                insertTodo(Todo(event.todo.title,event.todo.description,event.isDone,event.todo.time,event.todo.id))
            }
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.TODO_DETAIL+"?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnUndoTodoClick -> {}
        }
    }

     private fun deleteTodo(todo : Todo){
         viewModelScope.launch {
             repository.deleteTodo(todo)
         }
     }

     private fun insertTodo(todo : Todo){
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
     }

     private fun insertTodos(todos : List<Todo>){
        viewModelScope.launch {
            repository.insertTodos(todos)
        }
     }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch {
            _uiEvent.emit(uiEvent)
        }
    }
}