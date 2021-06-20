package com.example.firebaseproject.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.example.firebaseproject.Upload_Retrive.UploadItem
import com.example.firebaseproject.authfiles.LoginActivity
import com.example.firebaseproject.authfiles.changePasswordActivity
import com.example.firebaseproject.data.userFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : Fragment(R.layout.activity_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lateinit var logoutbutton: Button
        lateinit var mAuth: FirebaseAuth
        lateinit var db: DatabaseReference
        lateinit var changepassword: Button
        lateinit var user: TextView
        lateinit var upload: ImageButton
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        logoutbutton = view.findViewById(R.id.Logoutbutton)
        changepassword = view.findViewById(R.id.ChangePassword)
        user = view.findViewById(R.id.username)
        upload = view.findViewById(R.id.imageButton)
        db = FirebaseDatabase.getInstance().getReference("userName")
        db.child(mAuth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userinfo: userFile = snapshot.getValue(userFile::class.java)?:return
                user.text = "Hello " +  userinfo.name
            }

            override fun onCancelled(error: DatabaseError) {
                //nothing
            }

        })
        logoutbutton.setOnClickListener {
            startActivity(Intent(getActivity(), LoginActivity::class.java))
            mAuth.signOut()

        }

        changepassword.setOnClickListener {
            startActivity(Intent(getActivity(), changePasswordActivity::class.java))
        }
        upload.setOnClickListener {
            startActivity(Intent(getActivity(), UploadItem::class.java))
        }
    }
}

