package com.artemissoftware.pokeapi.di

import com.artemissoftware.pokeapi.service.NetworkService
import com.artemissoftware.pokeapi.viewmodel.PokemonViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(networkService: NetworkService)

    fun inject(pokemonViewModel: PokemonViewModel)
}