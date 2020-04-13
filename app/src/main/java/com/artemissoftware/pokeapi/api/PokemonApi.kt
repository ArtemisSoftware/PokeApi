package com.artemissoftware.pokeapi.api

import com.artemissoftware.pokeapi.models.PokedexResult
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon/?offset=5&limit=5")
    fun getPokedex(): Single<PokedexResult>

}