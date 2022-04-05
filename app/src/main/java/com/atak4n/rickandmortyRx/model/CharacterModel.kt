package com.atak4n.rickandmortyRx.model

data class CharacterModel(
    val image: String = "",
    val name: String = "",
    val status: String= "",
    val location: Location = Location(),
) {
    data class Location(
        val name: String = "",
        val url: String = ""

    )
}


