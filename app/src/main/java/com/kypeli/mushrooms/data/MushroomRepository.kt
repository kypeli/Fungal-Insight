package com.kypeli.mushrooms.data

import com.kypeli.mushrooms.model.Mushroom

object MushroomRepository {
    private val mushrooms =
        listOf(
            Mushroom(
                id = "1",
                name = "Fly Agaric",
                scientificName = "Amanita muscaria",
                description = "The fly agaric is a basidiomycete of the genus Amanita. It is also a muscimol mushroom. Native throughout the temperate and boreal regions of the Northern Hemisphere, Amanita muscaria has been unintentionally introduced to many countries in the Southern Hemisphere.",
            ),
            Mushroom(
                id = "2",
                name = "Chantereille",
                scientificName = "Cantharellus cibarius",
                description = "Cantharellus cibarius, commonly known as the chanterelle, golden chanterelle or Girolle, is a fungus. It is probably the best known species of the genus Cantharellus, if not the entire family of Cantharellaceae.",
            ),
            Mushroom(
                id = "3",
                name = "Porcini",
                scientificName = "Boletus edulis",
                description = "Boletus edulis is a basidiomycete fungus, and the type species of the genus Boletus. Widely distributed in the Northern Hemisphere across Europe, Asia, and North America, it does not occur naturally in the Southern Hemisphere, although it has been introduced to southern Africa, Australia, New Zealand and Brazil.",
            ),
        )

    fun getMushrooms(): List<Mushroom> = mushrooms

    fun getMushroom(id: String): Mushroom? = mushrooms.find { it.id == id }
}
