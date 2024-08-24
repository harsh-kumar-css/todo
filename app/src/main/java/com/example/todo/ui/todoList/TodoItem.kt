package com.example.todo.ui.todoList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.data.local.Todo


@Composable
fun TodoItem(
    todo: Todo,
    OnEvent: (TodoListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { OnEvent(TodoListEvent.OnTodoClick(todo)) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(.8f)
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text(text = todo.title)
                }
                todo.description?.let {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                    ) {
                        Text(text = it)
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = todo.time.toString())
                }
            }
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Checkbox(checked = todo.isDone, onCheckedChange = { isChecked ->
                        OnEvent(TodoListEvent.OnDoneClick(todo,isChecked))
                    })
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete",
                        modifier = Modifier.clickable { OnEvent(TodoListEvent.OnDeleteClick(todo)) }
                    )
                }
            }
        }

    }
}