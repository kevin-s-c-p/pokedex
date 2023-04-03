package com.example.pokedex.data.repositories

sealed class ResultWrapper<out T> {
    data class Success<out T>(val response: T?): ResultWrapper<T>()
    data class ErrorGeneric(val error: String? = null): ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}