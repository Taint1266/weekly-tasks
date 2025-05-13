package com.example.firebaserest


import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel(){
    private val repository = TaskRepository()
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        repository.getTasks { taskList ->
            _tasks.value = taskList
        }
    }
    fun addTask(title: String) {
        val newTask = Task(title = title)
        repository.addTask(newTask)
        fetchTasks()
    }
    fun updateTask(task: Task) {
        repository.updateTask(task.id, task)
        fetchTasks()
    }
    fun deleteTask(taskId: String) {
        repository.deleteTask(taskId)
        fetchTasks()
    }

}
