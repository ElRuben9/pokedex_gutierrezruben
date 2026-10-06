package gutierrez.ruben.pokedexcompose_gutierrrezruben.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.OffWhite
import gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities.getColorByType

@Composable
fun PokemonRow(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = "${pokemon.name.lowercase()} image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
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
            text = "${pokemon.number}",
            modifier = Modifier.align(Alignment.Top),
            colors = colors
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)

    val gradient = Brush.sweepGradient(
        colors = listOf(
            colors.first,
            OffWhite,
            colors.first,
            OffWhite,
            colors.first
        )
    )

    Column(
        modifier = Modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(BorderStroke(5.dp, gradient))
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = pokemon.image),
                    contentDescription = pokemon.name,
                    modifier = Modifier.width(75.dp)
                )
            }

            NumberChip(
                text = "${pokemon.number}",
                modifier = Modifier.align(Alignment.BottomEnd),
                colors = colors
            )
        }

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        NumberChip(
            text = "${pokemon.number}",
            modifier = Modifier.align(Alignment.TopEnd),
            colors = colors
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = pokemon.image),
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}