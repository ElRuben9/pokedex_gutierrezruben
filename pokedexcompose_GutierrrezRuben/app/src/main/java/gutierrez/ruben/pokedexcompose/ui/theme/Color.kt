package gutierrez.ruben.pokedexcompose.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val White = Color(0xFFFFFFFF)
val Pink = Color(0xFFFF8CEE)
val Grey = Color(0xFF313131)
val Red = Color(0xFFEF1717)

val OffWhite = Color(0xFFFAFAFA)
val DarkGray = Color(0xFF1F1F1F)

val Electric = Color(0xFFFFEB3B)
val Grass = Color(0xFF43A047)
val Fire = Color(0xFFE74440)
val Water = Color(0xFF00ACC1)
val Normal = Color(0xFF5D5631)
val Bug = Color(0xFF7CB342)
val Poison = Color(0xFF8E24AA)
val Ground = Color(0xFF983D21)
val Rock = Color(0xFF424241)
val Flying = Color(0xFF71A9D7)
val Fight = Color(0xFFFB8C00)
val Psych = Color(0xFFE86D95)
val Ghost = Color(0xFF5E35B1)
val Dragon = Color(0xFF3949AB)
val Dark = Color(0xFF131310)
val Ice = Color(0xFF9FA8DA)
val Fairy = Color(0xFFEF9A9A)

val Green = Color(0xFF81CA85)
val LightGreen = Color(0xFFCCFFCE)
val Blue = Color(0xFF4D92D2)
val LightBlue = Color(0xFFA5CEFD)

data class TypePalette(
    val background: Color,
    val content: Color
)

fun paletteFor(type: String): TypePalette = when (type) {
    "Normal" -> TypePalette(Normal, OffWhite)
    "Water" -> TypePalette(Water, OffWhite)
    "Fire" -> TypePalette(Fire, OffWhite)
    "Psychic" -> TypePalette(Psych, OffWhite)
    "Ghost" -> TypePalette(Ghost, OffWhite)
    "Dark" -> TypePalette(Dark, OffWhite)
    "Dragon" -> TypePalette(Dragon, OffWhite)
    "Bug" -> TypePalette(Bug, DarkGray)
    "Poison" -> TypePalette(Poison, DarkGray)
    "Grass" -> TypePalette(Grass, DarkGray)
    "Ground" -> TypePalette(Ground, DarkGray)
    "Rock" -> TypePalette(Rock, DarkGray)
    "Electric" -> TypePalette(Electric, DarkGray)
    "Fairy" -> TypePalette(Fairy, DarkGray)
    "Fight" -> TypePalette(Fight, DarkGray)
    "Flying" -> TypePalette(Flying, DarkGray)
    "Ice" -> TypePalette(Ice, DarkGray)
    else -> TypePalette(Color.Gray, OffWhite)
}
