package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var emailsubmit: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatpasswordEditText: EditText
    private lateinit var SignInButton: Button
    private lateinit var RegisterButton: Button

    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null) {
            this.GoToMainPage();
        }
        setContentView(R.layout.activity_login)
        this.init();
        this.RegisterListeners();
    }



    private fun init(){
        emailsubmit = findViewById(R.id.EmailAddress)
        passwordEditText = findViewById(R.id.TextPassword)
        repeatpasswordEditText = findViewById(R.id.TextPasswordRepeat)
        SignInButton = findViewById(R.id.signInButton)
        RegisterButton = findViewById(R.id.RegisterButton)
    }

    private fun RegisterListeners()
    {
       SignInButton.setOnClickListener{

           this.SignInActivity();
       }

        RegisterButton.setOnClickListener{
            val  email : String = emailsubmit.text.toString()
            val password: String = passwordEditText.text.toString()
            val RepeatPassword : String = repeatpasswordEditText.text.toString()
            if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this,"EmptyEmail",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.isEmpty() || password.length<9|| !password.matches("^(?=.*[a-z])(?=.*[0-9])[a-z0-9]+$".toRegex())) {
                Toast.makeText(this,"Password must be minimum 9 characters long and must contain numbers",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(RepeatPassword.isEmpty() || RepeatPassword != password){
                Toast.makeText(this, "make sure passwords match", Toast.LENGTH_SHORT).show()
            }
          mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
              task -> if(task.isSuccessful){
              GoToMainPage();
          }
              else{
              Toast.makeText(this,"O_O user already exists",Toast.LENGTH_SHORT).show()
          }
          }
        }

    }

    private fun GoToMainPage(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun SignInActivity(){
        startActivity(Intent(this,SignInActivity::class.java))
    }



}