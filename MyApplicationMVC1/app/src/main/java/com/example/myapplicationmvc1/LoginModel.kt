package com.example.myapplicationmvc1

class LoginModel {
    private val validUsername = "admin"
    private val validPassword = "1234"

    fun validateCredentials ( username: String, password : String): Boolean {
        return username ==validUsername && password == validPassword
    }
}