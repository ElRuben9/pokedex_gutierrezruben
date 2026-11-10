package gutierrez.ruben.pokedexcompose.data

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val number: Int,
    val type: String,
    val description: String,
    val height: Float,
    val weight: Float,
    val favorite: Boolean,
    val ability: String,
    val image: Int,
    val companionSince: String = ""
) {

    val exists: Boolean
        get() = number > 0

    companion object {
        const val NO_COMPANION = -1

        val None = Pokemon(
            name = "",
            number = NO_COMPANION,
            type = "",
            description = "",
            height = 0f,
            weight = 0f,
            favorite = false,
            ability = "",
            image = 0
        )
    }
}
