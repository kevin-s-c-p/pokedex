package com.example.pokedex.data.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ResultResponse>
)