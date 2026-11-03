package gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities

import androidx.compose.ui.graphics.Color
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Bug
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dark
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.DarkGray
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dragon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Electric
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Fairy
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Fight
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Fire
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Flying
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Ghost
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Grass
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Ground
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Ice
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Normal
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.OffWhite
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Poison
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Psych
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Rock
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Steel
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Water

fun getColorByType(type: String): Pair<Color, Color> =
    when (type.substringBefore('/')) {
        "Normal" -> Normal to OffWhite
        "Water" -> Water to OffWhite
        "Fire" -> Fire to OffWhite
        "Psych", "Psychic" -> Psych to OffWhite
        "Ghost" -> Ghost to OffWhite
        "Bug" -> Bug to OffWhite
        "Poison" -> Poison to OffWhite
        "Grass" -> Grass to OffWhite
        "Ground" -> Ground to OffWhite
        "Rock" -> Rock to OffWhite
        "Dragon" -> Dragon to OffWhite
        "Dark" -> Dark to OffWhite
        "Steel" -> Steel to OffWhite
        "Electric" -> Electric to DarkGray
        "Fairy" -> Fairy to DarkGray
        "Fight", "Fighting" -> Fight to DarkGray
        "Flying" -> Flying to DarkGray
        "Ice" -> Ice to DarkGray
        else -> Normal to OffWhite
    }
