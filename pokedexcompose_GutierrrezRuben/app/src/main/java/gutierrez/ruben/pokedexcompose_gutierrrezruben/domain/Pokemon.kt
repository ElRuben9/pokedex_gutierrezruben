package gutierrez.ruben.pokedexcompose_gutierrrezruben.domain

import androidx.annotation.DrawableRes

data class Pokemon(
    val name: String, val num: Int, val type: String,
    val description: String, val height: Float, val weight: Float,
    val favorite: Boolean, val ability: String, @DrawableRes val image: Int
)
