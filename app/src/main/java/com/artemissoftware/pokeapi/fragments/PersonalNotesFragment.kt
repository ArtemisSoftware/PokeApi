package com.artemissoftware.pokeapi.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.artemissoftware.pokeapi.R

class PersonalNotesFragment : Fragment() {
/*
    companion object {
        fun newInstance() = PersonalNotesFragment()
    }

    private lateinit var viewModel: PersonalNotesViewModel
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.personal_notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(PersonalNotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
