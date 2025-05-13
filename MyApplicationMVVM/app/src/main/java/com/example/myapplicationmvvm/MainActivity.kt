package com.example.myapplicationmvvm
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationmvvm.R
import com.example.myapplicationmvvm.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var usernameField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var rememberMeCheckBox: CheckBox

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameField = findViewById(R.id.txtUsername)
        passwordField = findViewById(R.id.txtPassword)
        loginButton = findViewById(R.id.btnLogin)
        rememberMeCheckBox = findViewById(R.id.chkRememberMe)

        loginViewModel.initialize(context = this)

        loginViewModel.savedUsername.obeserve(this) { savedUsername ->
           usernameField.setText(savedUsername)
        }



        loginViewModel.loginStatus.observe(owner = this) { isSuccess ->



            if (isSuccess) {
                Toast.makeText(context = this, text = "Login Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context = this, text = "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }

            loginButton.setOnClickListener {
                val username = usernameField.text.toString()
                val password = passwordField.text.toString()
                val rememberMe = rememberMeCheckBox.isChecked
                loginViewModel.onLoginClicked(username, password, rememberMe)
            }

            loginViewModel.loadSavedUsername()






        }





    }


}






