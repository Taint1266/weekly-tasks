package com.example.myapplicationmvc1

class LoginController(private val model: LoginModel, private val view: MainActivity) {
    fun onLoginButtonClicked(username: String, password: String){
        if (model.validateCredentials(username, password)){
            view.showSuccessMessage()
        } else {
            view.showErrorMessage()
        }

    }
}