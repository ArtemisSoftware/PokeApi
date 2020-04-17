package com.artemissoftware.pokeapi.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.artemissoftware.pokeapi.fragments.AboutFragment
import com.artemissoftware.pokeapi.fragments.PersonalNotesFragment

class ViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragmentList:MutableList<Fragment> = ArrayList<Fragment>()

    val fragmentTitleList:MutableList<String> = ArrayList<String>()

    init {
        addFragment(AboutFragment(),"About")
        addFragment(PersonalNotesFragment(),"Personal Notes")
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList.get(position)
    }



    // this counts total number of tabs
    override fun getCount(): Int {
        return return fragmentList.size
    }


    private fun addFragment(fragment:Fragment,title:String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)

    }
}