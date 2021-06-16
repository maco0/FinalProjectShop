package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class forgotPasswordActivity : AppCompatActivity() {
    lateinit var email : EditText
    lateinit var linkbutton: Button
    lateinit var back: Button
    lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        getLink()
        backButton()
    }

    private fun getLink(){
        email = findViewById(R.id.forgotPasswordEmailAddress)
        linkbutton = findViewById(R.id.linkButton)
        linkbutton.setOnClickListener {
            val emailtext = email.text.toString()
            if (emailtext.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
                Toast.makeText(this, "Wrong email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
         mAuth.sendPasswordResetEmail(emailtext).addOnCompleteListener {task ->
             if(task.isSuccessful){
                 Toast.makeText(this, "Check email", Toast.LENGTH_SHORT).show()
             }
             else{
                 Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
             }
         }
        }
    }
    private fun backButton(){
        back = findViewById(R.id.forgotPasswordBack)
        back.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}