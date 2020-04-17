package com.artemissoftware.pokeapi.api

import com.artemissoftware.pokeapi.models.PokedexResult
import com.artemissoftware.pokeapi.models.PokemonResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/?offset=0&limit=5")
    fun getPokedex(): Single<PokedexResult>


    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id : String): Single<PokemonResult>
}