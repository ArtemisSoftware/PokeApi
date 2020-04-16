package com.artemissoftware.pokeapi.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.artemissoftware.pokeapi.fragments.AboutFragment

class ViewPagerAdapter (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        var fragment: Fragment= AboutFragment()

        when (position) {
            0 -> fragment = AboutFragment()
            //else -> return AboutFragment()
        }

        return fragment
    }



    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}