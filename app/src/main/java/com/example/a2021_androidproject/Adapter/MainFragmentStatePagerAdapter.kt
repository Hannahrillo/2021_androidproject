package com.example.a2021_androidproject.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.bottomnav.Fragment.*
import com.example.a2021_androidproject.MainActivity
import com.example.a2021_androidproject.fragment.BmiFragment

class MainFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HomeFragment()
            1 -> return MainActivity()
            2 -> return BmiFragment()
            3 -> return MapsFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount // 자바에서는 { return fragmentCount }
}
