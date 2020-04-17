package com.artemissoftware.pokeapi.service

import com.artemissoftware.pokeapi.api.PokemonApi
import com.artemissoftware.pokeapi.di.DaggerApiComponent
import com.artemissoftware.pokeapi.models.PokedexResult
import com.artemissoftware.pokeapi.models.PokemonResult
import io.reactivex.Single
import javax.inject.Inject

class NetworkService {

    @Inject
    lateinit var pokemonApi: PokemonApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchPokedex(): Single<PokedexResult> {
        return pokemonApi.getPokedex()
    }

    fun fetchPokemon(id: String): Single<PokemonResult> {
        return pokemonApi.getPokemon(id)
    }



    companion object {
        val BASE_URL = "https://pokeapi.co/api/v2/"
    }

}