package com.example.pokedex.data.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("sprites")
    val sprites: SpritesResponse,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("name")
    val name: String
)