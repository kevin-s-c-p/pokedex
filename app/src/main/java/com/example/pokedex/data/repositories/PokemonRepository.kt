package com.example.pokedex.data.repositories

import com.example.pokedex.data.network.NetworkModule

class PokemonRepository: BaseRepository() {
    suspend fun requestPokemon() = apiCall { NetworkModule.create().requestPokemon() }

    suspend fun requestDetailPokemon(idPokemon: Int) = apiCall {
        NetworkModule.create().requestDetailPokemon(idPokemon.toString())
    }
    suspend fun requestDetailPokemon(namePokemon: String) = apiCall {
        NetworkModule.create().requestDetailPokemon(namePokemon)
    }

    suspend fun requestDescriptionPokemon(idPokemon: Int) = apiCall {
        NetworkModule.create().requestDescriptionPokemon(idPokemon.toString())
    }
}