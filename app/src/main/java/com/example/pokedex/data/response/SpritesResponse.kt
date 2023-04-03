package com.example.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("front_default")
    val imagePokemon: String
)