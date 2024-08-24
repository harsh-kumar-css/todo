package com.example.todo.ui.todoDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.local.Todo
import com.example.todo.domain.repository.TodoRepository
import com.example.todo.util.AlarmManager.AlarmScheduler
import com.example.todo.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val alarmScheduler: AlarmScheduler,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    var todo = mutableStateOf<Todo?>(null)
    private set

    var title = mutableStateOf("")
    private set

    var description = mutableStateOf("")
    private set

    var time = mutableStateOf(-1L)
    private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")

        if(todoId != -1)
        {
            viewModelScope.launch {
                if (todoId != null) {
                    todoRepository.getTodo(todoId).let{ todo ->
                        title.value = todo.title
                        description.value = todo.description ?: ""
                        time.value = todo.time
                        this@TodoDetailViewModel.todo.value = todo
                    }
                }
            }
        }
    }


    fun onEvent(event: TodoDetailEvent) {
        when (event) {
            is TodoDetailEvent.OnDateChange -> {
                time.value = event.time
            }

            is TodoDetailEvent.OnDescriptionChange -> {
                description.value = event.description
            }

            is TodoDetailEvent.OnSaveTodoClick -> {
                val todoItem = Todo(
                    title.value,
                    description.value,
                    todo.value?.isDone ?: false,
                    time.value,
                    todo.value?.id
                )

                println("fireedddd")
                if (todo.value?.time != time.value && time.value != -1L) alarmScheduler.schedule(
                    todoItem
                )
                else {
                    insertTodo(
                        todoItem
                    )
                }
                sendUiEvent(UiEvent.PopBackStack)
            }

            is TodoDetailEvent.OnTitleChange -> {
                title.value = event.title
            }

            is TodoDetailEvent.AddTodoInFuture -> {
                println("gffjfgjfjfjfgffgjjf")
            }
            else -> {}
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(uiEvent)
        }
    }

    private fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
        }
    }

}