package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PokedexColorScheme = lightColorScheme(
    primary = PokedexYellow,
    onPrimary = Color.White,
    secondary = PokedexRed,
    onSecondary = Color.White,
    background = PokedexYellow,
    onBackground = TextNumber,
    surface = Color.White,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
)

@Composable
fun MiPokedexTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PokedexColorScheme,
        typography = PokedexTypography,
        content = content,
    )
}
