package com.example.pokedex.ui.home.home

import BaseViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repositories.PokemonRepository
import com.example.pokedex.data.repositories.ResultWrapper
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val repository = PokemonRepository()

    fun requestPokemon() {
        viewModelScope.launch {
            repository.requestPokemon().collect {
                when(it) {
                    is ResultWrapper.ErrorGeneric -> {  }
                    is ResultWrapper.NetworkError -> {  }
                    is ResultWrapper.Success -> { updateValueResponse(it.response!!) }
                }
            }
        }
    }

    fun requestPokemonDetail(idPokemon: Int) {
        updateProgress(PROGRESS_START)
        viewModelScope.launch {
            repository.requestDetailPokemon(idPokemon).collect {
                when(it) {
                    is ResultWrapper.ErrorGeneric -> {  }
                    is ResultWrapper.NetworkError -> {  }
                    is ResultWrapper.Success -> { updateValueResponse(it.response!!) }
                }
            }
        }
    }

    fun requestPokemonDetail(namePokemon: String) {
        viewModelScope.launch {
            repository.requestDetailPokemon(namePokemon).collect {
                when(it) {
                    is ResultWrapper.ErrorGeneric -> {  }
                    is ResultWrapper.NetworkError -> {  }
                    is ResultWrapper.Success -> { updateValueResponse(it.response!!) }
                }
            }
        }
    }

    fun requestPokemonDescription(idPokemon: Int) {
        viewModelScope.launch {
            repository.requestDescriptionPokemon(idPokemon).collect{
                when(it) {
                    is ResultWrapper.ErrorGeneric -> {  }
                    is ResultWrapper.NetworkError -> {  }
                    is ResultWrapper.Success -> { updateValueResponse(it.response!!) }
                }
            }
        }
    }
}