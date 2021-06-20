package com.example.firebaseproject.authfiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebaseproject.fragments.FragmentContainer
import com.example.firebaseproject.R
import com.example.firebaseproject.data.userFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var emailsubmit: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatpasswordEditText: EditText
    private lateinit var SignInButton: Button
    private lateinit var RegisterButton: Button
    private lateinit var forgotPassword: Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var db : DatabaseReference
    private lateinit var name: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("userName")
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
        forgotPassword = findViewById(R.id.ForgotPassword)
        name = findViewById(R.id.name)
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
            val name : String = name.text.toString()
            if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this,"EmptyEmail",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(password.isEmpty() || password.length<9) {

                Toast.makeText(this,"Password must be minimum 9 characters long",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if(RepeatPassword.isEmpty() || RepeatPassword != password){
                Toast.makeText(this, "make sure passwords match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (name.isEmpty()){
                Toast.makeText(this, "empty name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task -> if(task.isSuccessful){
                GoToMainPage();
                givename(name)
            }
            else
            {
                Toast.makeText(this,"O_O user already exists",Toast.LENGTH_SHORT).show()
            }
            }

        }

        forgotPassword.setOnClickListener {
            this.GoToForgotPasswordActivity()
        }

    }

    private fun givename(name: String) {
        val info =  userFile(name)
        db.child(mAuth.currentUser?.uid!!).setValue(info)
    }

    private fun GoToMainPage(){
        startActivity(Intent(this, FragmentContainer::class.java))
        finish()
    }

    private fun SignInActivity(){
        startActivity(Intent(this, SignInActivity::class.java))
    }

    private fun GoToForgotPasswordActivity(){
        startActivity(Intent(this, forgotPasswordActivity::class.java))
    }



}