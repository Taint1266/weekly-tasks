package com.example.firebaserest

import com.google.firebase.firestore.FirebaseFirestore

class TaskRepository {
    private val db = FirebaseFirestore.getInstance()
    private val tasksCollection = db.collection("tasks")

    fun addTask(task: Task) {
        tasksCollection.add(task)
    }

    fun getTasks(callback: (List<Task>) -> Unit) {
        tasksCollection.get().addOnSuccessListener { snapshot ->
            val tasks = snapshot.documents.mapNotNull { it.toObject(Task::class.java) }
            callback(tasks)
        }
    }

    fun updateTask(taskId: String, updatedTask: Task) {
        tasksCollection.document(taskId).set(updatedTask)
    }

    fun deleteTask(taskId: String) {
        tasksCollection.document(taskId).delete()
    }
}