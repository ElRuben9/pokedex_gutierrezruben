package gutierrez.ruben.pokedexcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.ui.theme.Ghost
import gutierrez.ruben.pokedexcompose.ui.theme.Green
import gutierrez.ruben.pokedexcompose.ui.theme.Psych
import gutierrez.ruben.pokedexcompose.ui.theme.Purple40
import gutierrez.ruben.pokedexcompose.ui.theme.Typography
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonHuntViewModel

@Composable
fun PokemonHuntScreen(
    innerPadding: PaddingValues,
    viewModel: PokemonHuntViewModel,
    onPokemonClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = viewModel::search) {
            Text("Buscar Pokémon en la hierba")
        }

        val wild = viewModel.wildPokemon
        if (wild != null) {
            Spacer(Modifier.height(12.dp))
            Image(
                painter = painterResource(wild.image),
                contentDescription = "${wild.name} image",
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = "Apareció un ${wild.name} salvaje",
                style = Typography.labelLarge,
                color = Purple40
            )
            Spacer(Modifier.height(12.dp))
            Button(onClick = viewModel::capture) {
                Text("Capturar a ${wild.name}")
            }
        }

        when (viewModel.lastCatchSucceeded) {
            true -> HuntMessage(text = "¡Atrapaste al Pokémon!", color = Green)
            false -> HuntMessage(text = "El Pokémon se escapó…", color = Psych)
            null -> Unit
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Pokémon capturados",
            style = Typography.titleLarge,
            color = Ghost
        )

        Spacer(Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(viewModel.capturedPokemons) { pokemon ->
                CapturedRow(pokemon = pokemon, onPokemonClick = onPokemonClick)
            }
        }
    }
}

@Composable
private fun HuntMessage(text: String, color: Color) {
    Text(
        text = text,
        modifier = Modifier.padding(top = 10.dp),
        color = color,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
}

@Composable
private fun CapturedRow(pokemon: Pokemon, onPokemonClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPokemonClick(pokemon.number) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier.size(56.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(text = pokemon.name, style = Typography.labelLarge)
            Text(text = "N.º ${pokemon.number}", style = Typography.labelMedium)
        }
    }
}
