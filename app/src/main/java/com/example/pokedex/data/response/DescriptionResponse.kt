package com.example.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class DescriptionResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: LanguageResponse
)