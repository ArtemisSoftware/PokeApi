package com.artemissoftware.pokeapi.service

import com.artemissoftware.pokeapi.api.PokemonApi
import com.artemissoftware.pokeapi.models.PokedexResult
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

    companion object {
        val BASE_URL = "https://pokeapi.co/api/v2/"
    }

}