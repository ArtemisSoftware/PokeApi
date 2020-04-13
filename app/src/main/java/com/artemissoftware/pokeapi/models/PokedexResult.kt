package com.artemissoftware.pokeapi.models

data class PokedexResult (

    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonResult>
)
