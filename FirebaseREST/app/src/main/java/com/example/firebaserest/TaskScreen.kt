// TaskScreen.kt
package com.example.firebaserest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskScreen(viewModel: TaskViewModel, modifier: Modifier = Modifier) {
    val tasks by viewModel.tasks.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp) // Added padding for better alignment
    ) {
        Text(
            text = "Task Management App",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 16.dp) // Added space below heading
        )

        var newTaskTitle by remember { mutableStateOf("") }

        OutlinedTextField(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            label = { Text(text = "Task Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp) // Space between input and button
        )

        Button(
            onClick = {
                if (newTaskTitle.isNotBlank()) {
                    viewModel.addTask(newTaskTitle)
                    newTaskTitle = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp) // Space before list
        ) {
            Text(text = "Add Task")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasks) { task ->
                TaskItem(task, viewModel)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, viewModel: TaskViewModel) {
    var isChecked by remember { mutableStateOf(task.isCompleted) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Text(
            text = task.title,
            modifier = Modifier.weight(1f),
            fontSize = 18.sp
        )

        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                viewModel.updateTask(task.copy(isCompleted = it))
            }
        )

        IconButton(onClick = { viewModel.deleteTask(task.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
