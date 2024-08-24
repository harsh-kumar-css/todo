import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.ui.todoDetail.TodoDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*

@Composable
fun DateTimePicker(
    viewModel: TodoDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var year by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    var day by remember { mutableStateOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) }
    var hour by remember { mutableStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    var minute by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }

    // Date Picker
    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                year = selectedYear
                month = selectedMonth
                day = selectedDay
                showDatePicker = false
                showTimePicker = true
            },
            year,
            month,
            day
        ).show()
    }

    // Time Picker
    if (showTimePicker) {
        TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                hour = selectedHour
                minute = selectedMinute

                // Combine date and time
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, day)
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                    set(Calendar.SECOND, 0)
                }
                viewModel.time.value = calendar.timeInMillis
                showTimePicker = false
            },
            hour,
            minute,
            true
        ).show()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedButton(onClick = { showDatePicker = true }) {
            Text("Pick Date and Time")
        }

        Text("Selected Date and Time in milliseconds: ${viewModel.time.value}")
    }
}