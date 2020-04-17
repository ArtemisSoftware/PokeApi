package com.artemissoftware.pokeapi.fragments

import androidx.fragment.app.Fragment

open abstract class BaseFragment : Fragment(){

    abstract fun getTitle() : String

}