package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
     private lateinit var logoutbutton: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_main)
         this.logoutBUtton()
    }

    private fun logoutBUtton(){
        logoutbutton = findViewById(R.id.Logoutbutton)
        logoutbutton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            mAuth.signOut()
            finish()
        }
    }
}