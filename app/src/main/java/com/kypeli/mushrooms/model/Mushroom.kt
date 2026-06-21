package com.kypeli.mushrooms.model

import kotlinx.serialization.Serializable

@Serializable
data class Mushroom(
    val id: String,
    val name: String,
    val scientificName: String,
    val description: String,
    val imageRes: Int? = null,
)
