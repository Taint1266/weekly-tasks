package com.example.firebaserest
import com.google.firebase.firestore.DocumentId

data class Task(
    @DocumentId val id: String = "",
    val title: String = "",
    val isCompleted: Boolean = false
)

