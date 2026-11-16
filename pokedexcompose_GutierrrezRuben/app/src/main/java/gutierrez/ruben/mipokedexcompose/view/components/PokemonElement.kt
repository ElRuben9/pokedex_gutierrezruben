package gutierrez.ruben.mipokedexcompose.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.mipokedexcompose.model.data.absol
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import gutierrez.ruben.mipokedexcompose.ui.theme.OffWhite
import gutierrez.ruben.mipokedexcompose.ui.theme.Typography
import gutierrez.ruben.mipokedexcompose.utilities.getColorByType

@Composable
fun PokemonRow(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToDetail(pokemon.number.toInt()) }
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = pokemon.name + " image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = pokemon.name,
                style = Typography.labelLarge
            )
            Text(
                text = pokemon.description,
                fontSize = 10.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Height: " + pokemon.height,
                    style = Typography.labelMedium
                )
                Text(
                    text = "Weight: " + pokemon.weight,
                    style = Typography.labelMedium
                )
            }
        }
        val pokemonColors = getColorByType(pokemon.type)
        NumberChip(
            texto = pokemon.number.toString(),
            colors = pokemonColors,
            modifier = Modifier.align(Alignment.Top)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    val pokemonColors = getColorByType(pokemon.type)
    Column(Modifier.width(150.dp).padding(vertical = 15.dp)
        .clickable(true, onClick = {onNavigateToDetail(pokemon.number as Int)})
        , verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box() {
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 5.dp,
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    pokemonColors.first,
                                    OffWhite,
                                    pokemonColors.first,
                                    OffWhite,
                                    pokemonColors.first
                                )
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = pokemon.image),
                    contentDescription = pokemon.name + " image",
                    modifier = Modifier
                        .width(75.dp)
                        .padding(5.dp)
                )
            }
            NumberChip(
                texto = pokemon.number.toString(),
                colors = pokemonColors,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(15.dp, 15.dp)
            )
        }
        Text(
            text = pokemon.name,
            style = Typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    val pokemonColors = getColorByType(pokemon.type)
    Column(
        Modifier.clickable(true, onClick = {onNavigateToDetail(pokemon.number as Int)}),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box() {
            Image(
                painter = painterResource(id = pokemon.image),
                contentDescription = pokemon.name + " image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Fit
            )
            NumberChip(
                texto = pokemon.number.toString(),
                colors = pokemonColors,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(10.dp, -10.dp)
            )
        }
        Text(
            text = pokemon.name,
            style = Typography.labelLarge
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PokemonElementPreview(){
//    PokemonCell(absol)
//}