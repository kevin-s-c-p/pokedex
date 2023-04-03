package com.example.pokedex.data.services

import com.example.pokedex.data.response.PokemonDescriptionResponse
import com.example.pokedex.data.response.PokemonDetailResponse
import com.example.pokedex.data.response.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Pokemons {
    @GET("pokemon/")
    fun requestPokemon(): Call<PokemonListResponse>

    @GET("pokemon/{namePokemon}/")
    fun requestDetailPokemon(
        @Path("namePokemon") namePokemon: String
    ): Call<PokemonDetailResponse>

    @GET("characteristic/{idPokemon}/")
    fun requestDescriptionPokemon(
        @Path("idPokemon") idPokemon: String
    ): Call<PokemonDescriptionResponse>
}