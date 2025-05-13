package com.example.myapplicationmvc1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtuser: EditText
    private lateinit var txtpsw: EditText
    private lateinit var loginButton: Button
    private lateinit var controller: LoginController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtuser = findViewById(R.id.editTextText4)
        txtpsw=findViewById(R.id.editTextText5)
        loginButton = findViewById(R.id.button2)

        val model = LoginModel()
        controller=LoginController(model, this)

        loginButton.setOnClickListener {
            val username = txtuser.text.toString()
            val password = txtpsw .text.toString()


        }


    }
    fun showSuccessMessage(){
        Toast.makeText(this,"Login Sucessful!", Toast.LENGTH_SHORT).show()
    }

    fun showErrorMessage(){
        Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show()

    }








}
