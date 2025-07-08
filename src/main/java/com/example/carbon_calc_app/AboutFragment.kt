package com.example.carbon_calc_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_about,container,false)
        val btn = view.findViewById<Button>(R.id.calculateBtn)

        btn.setOnClickListener{
            val homeFragment = HomeFragment()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, homeFragment)
            transaction.commit()

            val bottomNavBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavBar)
            bottomNavBar.selectedItemId = R.id.home
        }
        return view
    }


}