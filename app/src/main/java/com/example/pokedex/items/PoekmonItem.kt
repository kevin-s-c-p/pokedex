package com.example.pokedex.items

import java.io.Serializable

data class PoekmonItem(
    val id: Int,
    val imagePokemon: String,
    val namePokemon: String,
    var description: String,
    val height: String = "",
    val weight: String = ""
): Serializable
