package com.example.firebasefcmdemo

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.messaging.FirebaseMessaging

class FCMViewModel : ViewModel() {

    fun getFCMToken(context: Context, onTokenReceived: (String) -> Unit) {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(tag = "FCM", msg = "Fetching FCM token failed", task.exception)
                    return@addOnCompleteListener
                }

                val token = task.result
                onTokenReceived(token)
                Log.d(tag = "FCM", msg = "FCM Token: $token")
            }
    }
}
