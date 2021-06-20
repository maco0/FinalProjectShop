package com.example.firebaseproject.authfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebaseproject.R
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
                Toast.makeText(this, "Password empty or shorter than 9 letters", Toast.LENGTH_SHORT).show()
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
            onBackPressed()
        }
    }
}