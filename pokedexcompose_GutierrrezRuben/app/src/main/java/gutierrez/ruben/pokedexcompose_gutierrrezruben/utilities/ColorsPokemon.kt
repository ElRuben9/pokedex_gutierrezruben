package gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities

import androidx.compose.ui.graphics.Color
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.*

fun getColorByType(type: String): Pair<Color, Color> {
    val cleanType = type.split("/").first().trim()

    return when (cleanType) {
        "Electric" -> Pair(Electric, DarkGray)
        "Grass" -> Pair(Grass, OffWhite)
        "Fire" -> Pair(Fire, OffWhite)
        "Water" -> Pair(Water, OffWhite)
        "Normal" -> Pair(Normal, OffWhite)
        "Bug" -> Pair(Bug, DarkGray)
        "Poison" -> Pair(Poison, OffWhite)
        "Ground" -> Pair(Ground, OffWhite)
        "Rock" -> Pair(Rock, OffWhite)
        "Flying" -> Pair(Flying, DarkGray)
        "Fight" -> Pair(Fight, DarkGray)
        "Psychic", "Psych" -> Pair(Psych, OffWhite)
        "Ghost" -> Pair(Ghost, OffWhite)
        "Dragon" -> Pair(Dragon, OffWhite)
        "Dark" -> Pair(Dark, OffWhite)
        "Ice" -> Pair(Ice, OffWhite)
        "Fairy" -> Pair(Fairy, DarkGray)
        else -> Pair(Grass, OffWhite)
    }
}