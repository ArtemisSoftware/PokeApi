package com.artemissoftware.pokeapi.di

import com.artemissoftware.pokeapi.MainActivity
import com.artemissoftware.pokeapi.PokemonActivity
import com.artemissoftware.pokeapi.service.NetworkService
import com.artemissoftware.pokeapi.viewmodel.PokemonViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(networkService: NetworkService)

    fun inject(pokemonViewModel: PokemonViewModel)

    fun inject(mainActivity: MainActivity)

    fun inject(pokemonActivity: PokemonActivity)
}