package com.example.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonDescriptionResponse(
    @SerializedName("descriptions")
    val descriptions: List<DescriptionResponse>,
    @SerializedName("id")
    val id: Int
)