package gutierrez.ruben.pokedexcompose_gutierrrezruben.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.NumberChip
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities.getColorByType
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.OffWhite

@Composable
fun PokemonRow(pokemon: Pokemon) {
    val pokemonType = pokemon.type
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = "${pokemon.name} image",
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
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = pokemon.description,
                fontSize = 10.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Height: ${pokemon.height}",
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    text = "Weight: ${pokemon.weight}",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        NumberChip(
            text = pokemon.num.toString(),
            modifier = Modifier.align(Alignment.Top),
            colors = getColorByType(pokemonType)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    val pokemonType = pokemon.type
    val typeColors = getColorByType(pokemonType)

    Column(
        modifier = Modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Box(
                modifier = Modifier
                    .width(75.dp)
                    .border(
                        BorderStroke(
                            5.dp,
                            Brush.sweepGradient(
                                listOf(
                                    typeColors[0],
                                    OffWhite,
                                    typeColors[0],
                                    OffWhite,
                                    typeColors[0],
                                    OffWhite,
                                    typeColors[0]
                                )
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier.padding(5.dp)
                )
            }

            NumberChip(
                text = pokemon.num.toString(),
                colors = typeColors
            )
        }

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable

fun PokemonCell(pokemon: Pokemon, onNavigateToDetail: (Int) -> Unit) {
    val pokemonType = pokemon.type
    val typeColors = getColorByType(pokemonType)

    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(10.dp)
            .clickable(true, onClick = { onNavigateToDetail(pokemon.num) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
            )

            NumberChip(
                text = pokemon.num.toString(),
                colors = typeColors
            )
        }

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonRowPreview() {
    PokemonRow(pokemon = pokemonList[0])
}

@Preview(showBackground = true)
@Composable
fun FavoritePokemonPreview() {
    FavoritePokemon(pokemon = pokemonList[0], onNavigateToDetail = {})
}

@Preview(showBackground = true)
@Composable
fun PokemonCellPreview() {
    PokemonCell(pokemon = pokemonList[0], onNavigateToDetail = {})
}