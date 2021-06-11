package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginbutton: Button
    private lateinit var Backbutton: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_sign_in)
        this.init()
        this.initializeButtons()
    }


    private fun init() {
        emailText = findViewById(R.id.LoginEmailAdress)
        passwordText = findViewById(R.id.LoginPassword)
        loginbutton = findViewById(R.id.LoginButton)
        Backbutton = findViewById(R.id.BackButton)

    }

    private fun GoToMainPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun initializeButtons() {
        loginbutton.setOnClickListener {
            val email: String = emailText.text.toString()
            val password: String = passwordText.text.toString()
            if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "EmptyEmail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    GoToMainPage();
                }
                else{
                    Toast.makeText(this, "no such user", Toast.LENGTH_SHORT).show()
                }
            }

        }
        Backbutton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
