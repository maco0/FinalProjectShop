package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class changePasswordActivity : AppCompatActivity() {
    lateinit var newPasswordButton : Button
    lateinit var passwordChangeBack : Button
    lateinit var newPasswordText : EditText
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_change_password)
        this.init()
        updatePassword()
        backButton()
    }


    private fun init(){
        newPasswordButton= findViewById(R.id.updatePassword)
        newPasswordText = findViewById(R.id.newTextPassword)
        passwordChangeBack = findViewById(R.id.passwordChangeBackButton)
    }

    private  fun updatePassword(){
      newPasswordButton.setOnClickListener {
          val password = newPasswordText.text.toString()
          if(password.isEmpty() || password.length < 9){
              return@setOnClickListener
          }
          mAuth.currentUser?.updatePassword(password)?.addOnCompleteListener{
              task ->
              if(task.isSuccessful){
                  Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show()
              }
              else{
                  Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
              }
          }
      }
    }

    private fun backButton(){
        passwordChangeBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}