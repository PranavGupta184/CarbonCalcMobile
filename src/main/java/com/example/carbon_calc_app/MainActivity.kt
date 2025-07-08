package com.example.carbon_calc_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment())

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        bottomNavBar.setOnItemSelectedListener { item->
            when(item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                }
                R.id.contact -> {
                    replaceFragment(ContactFragment())
                }

            }
            return@setOnItemSelectedListener true
        }


    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }
}