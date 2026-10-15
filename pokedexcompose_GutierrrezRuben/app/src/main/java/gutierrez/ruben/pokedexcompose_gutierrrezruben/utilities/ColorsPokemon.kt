package gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities

import androidx.compose.ui.graphics.Color
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.*


fun getColorByType(tipoPokemon: String): Array<Color> {

    val tipoPokemonLower = tipoPokemon.trim().lowercase()

    val primerTipo = if (tipoPokemonLower.contains("/")) {
        tipoPokemonLower.substringBefore("/")
    } else {
        tipoPokemonLower
    }

    val colorMap: HashMap<String, Array<Color>> = hashMapOf(
        "normal" to arrayOf(Normal, OffWhite),
        "water" to arrayOf(Water, OffWhite),
        "fire" to arrayOf(Fire, OffWhite),
        "psychic" to arrayOf(Psych, OffWhite),
        "ghost" to arrayOf(Ghost, OffWhite),
        "bug" to arrayOf(Bug, OffWhite),
        "poison" to arrayOf(Poison, OffWhite),
        "grass" to arrayOf(Grass, OffWhite),
        "ground" to arrayOf(Ground, OffWhite),
        "rock" to arrayOf(Rock, OffWhite),
        "electric" to arrayOf(Electric, DarkGray),
        "fairy" to arrayOf(Fairy, DarkGray),
        "fight" to arrayOf(Fight, DarkGray),
        "flying" to arrayOf(Flying, DarkGray)

    )

    return colorMap[primerTipo] ?: arrayOf(DarkGray, OffWhite)
}