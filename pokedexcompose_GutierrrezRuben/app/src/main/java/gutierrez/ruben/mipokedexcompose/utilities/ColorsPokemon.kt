package gutierrez.ruben.mipokedexcompose.utilities

import androidx.compose.ui.graphics.Color
import gutierrez.ruben.mipokedexcompose.ui.theme.*

fun getColorByType(type: String): Pair<Color, Color> {
    return when (type) {
        "Normal" -> Pair(Normal, OffWhite)
        "Water" -> Pair(Water, OffWhite)
        "Fire" -> Pair(Fire, OffWhite)
        "Psychic" -> Pair(Psych, OffWhite)
        "Ghost" -> Pair(Ghost, OffWhite)
        "Dark" -> Pair(Dark, OffWhite)
        "Dragon" -> Pair(Dragon, OffWhite)
        "Bug" -> Pair(Bug, DarkGray)
        "Poison" -> Pair(Poison, DarkGray)
        "Grass" -> Pair(Grass, DarkGray)
        "Ground" -> Pair(Ground, DarkGray)
        "Rock" -> Pair(Rock, DarkGray)
        "Electric" -> Pair(Electric, DarkGray)
        "Fairy" -> Pair(Fairy, DarkGray)
        "Fight" -> Pair(Fight, DarkGray)
        "Flying" -> Pair(Flying, DarkGray)
        "Ice" -> Pair(Ice, DarkGray)
        else -> Pair(Color.Gray, OffWhite)
    }
}