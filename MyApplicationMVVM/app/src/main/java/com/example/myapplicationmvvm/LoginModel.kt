package com.example.myapplicationmvvm

import android.content.Context
import android.content.SharedPreferences

class LoginViewModel(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    private val validUsername = "admin"
    private val validPassword = "1234"

    fun validateCredentials(username: String,password: String): Boolean{
        return username == validUsername && password == validPassword
    }

    fun saveUsername(username: String){
        preferences.edit().putString("SAVED_USERNAME",username).apply()
    }


}



class LoginView(context: Context) {
    private val prefrences: SharedPreferences =
        context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    private val validUsername = "admin"
    private val validPassword = "1234"

    fun validateCredentials(username: String, password: String): Boolean {
        return username == validUsername && password == validPassword
    }

    fun saveUsername(username: String) {
        prefrences.edit().putString("SAVED_USERNAME", username).apply()
    }

    fun getSavedUsername(): String {
        return prefrences.getString("SAVED_USERNAME", "") ?: ""
    }
}