package com.example.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.ui.theme.TodoTheme
import com.example.todo.ui.todoDetail.TodoDetailScreen
import com.example.todo.ui.todoList.TodoListScreen
import com.example.todo.util.AlarmManager.AlarmSchedulerImpl
import com.example.todo.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.TODO_LIST) {
                    composable(route = Routes.TODO_LIST) {
                        TodoListScreen(
                            onNavigate = { navController.navigate(it)
                            }
                        )
                    }
                    composable(route = Routes.TODO_DETAIL + "?todoId={todoId}", arguments =
                    listOf(
                        navArgument("todoId") {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                    ) {
                        TodoDetailScreen(popBackStack = { navController.popBackStack() } )
                    }
                }
            }
        }
    }
}
