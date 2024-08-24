package com.example.todo.ui.todoList

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.data.local.Todo
import com.example.todo.util.NotificationManager.NotificationHandler
import com.example.todo.util.UiEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TodoListScreen(
    onNavigate : (String) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val todos = viewModel.todoList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit){
        if(!postNotificationPermission.status.isGranted){
            postNotificationPermission.launchPermissionRequest()
        }

        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                is UiEvent.PopBackStack -> {}
                is UiEvent.ShowSnackBar -> {}
            }
        }

    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.OnEvent(TodoListEvent.OnAddTodoClick)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
        },
        modifier = Modifier.fillMaxSize()
    ){ it->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)) {
            items(todos.value){ todo ->
                    TodoItem(todo, viewModel::OnEvent)
            }
        }
    }
    
}