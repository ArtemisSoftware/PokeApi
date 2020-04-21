package com.artemissoftware.pokeapi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.artemissoftware.pokeapi.fragments.AboutFragment
import com.artemissoftware.pokeapi.fragments.BaseFragment
import com.artemissoftware.pokeapi.fragments.NotesFragment
import com.artemissoftware.pokeapi.models.Note
import com.artemissoftware.pokeapi.models.PokemonResult

class ViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragmentList:MutableList<BaseFragment> = ArrayList<BaseFragment>()


    init {
        addFragment(AboutFragment())
        addFragment(NotesFragment())
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList.get(position).getTitle()
    }



    // this counts total number of tabs
    override fun getCount(): Int {
        return return fragmentList.size
    }


    private fun addFragment(fragment: BaseFragment){
        fragmentList.add(fragment)
    }


    fun updatePokemon(pokemon : PokemonResult){
        (fragmentList.get(0) as AboutFragment).updatePokemon(pokemon)
    }

    fun updateNotes(notes : List<Note>){
        (fragmentList.get(1) as NotesFragment).updateNotes(notes)
    }
}