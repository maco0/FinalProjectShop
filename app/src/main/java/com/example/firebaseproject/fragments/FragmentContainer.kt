package com.example.firebaseproject.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.firebaseproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentContainer : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)

        moveToFragment(MainActivity())
        bottomNavigationView = findViewById(R.id.bottomnav)
        var itemSelected = BottomNavigationView.OnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.mainActivity ->{
                    moveToFragment(MainActivity())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.displayActivity ->{
                    moveToFragment(displayActivity())
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelected)

    }

    private fun moveToFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.host_fragment, fragment)
        fragmentTransition.commit()
    }

}