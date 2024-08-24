package com.example.todo.ui.todoDetail

import DateTimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    popBackStack: () -> Unit,
    viewModel: TodoDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {

                }
                UiEvent.PopBackStack -> {
                    popBackStack()
                }

                is UiEvent.ShowSnackBar -> {}
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TodoDetailEvent.OnSaveTodoClick)
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "save")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Row() {
                        OutlinedTextField(
                            value = viewModel.title.value,
                            label = { Text(text = "Enter Title")},
                            onValueChange = { viewModel.onEvent(TodoDetailEvent.OnTitleChange(it)) })
                    }
                    Row() {
                        OutlinedTextField(
                            value = viewModel.description.value,
                            label = { Text(text = "Enter Description")},
                            onValueChange = {
                                viewModel.onEvent(
                                    TodoDetailEvent.OnDescriptionChange(it)
                                )
                            })
                    }
                    Row() {
                        DateTimePicker()
                    }
                }
            }
        }
    }
}