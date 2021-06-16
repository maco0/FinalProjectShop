package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
     private lateinit var logoutbutton: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var changepassword: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_main)
         this.logoutBUtton()
        this.changePass()
    }

    private fun logoutBUtton(){
        logoutbutton = findViewById(R.id.Logoutbutton)
        logoutbutton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            mAuth.signOut()
            finish()
        }
    }

    private fun changePass(){
        changepassword = findViewById(R.id.ChangePassword)
        changepassword.setOnClickListener {
            startActivity(Intent(this,changePasswordActivity::class.java))
        }
    }
}