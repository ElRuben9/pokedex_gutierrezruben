package gutierrez.ruben.pokedexcompose_gutierrrezruben.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.FavriteRow
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.PokedexGrid
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList


@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToHunt: () -> Unit
) {
    val favoritePokemons = pokemonList.take(5)


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onNavigateToHunt) {
                Text("New Capture")
            }
        }
        Text(
            text = "Mis favoritos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        FavriteRow(favoriteList = favoritePokemons, onNavigateToDetail = onNavigateToDetail)

        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        PokedexGrid(pokemonList = pokemonList, onNavigateToDetail = onNavigateToDetail)
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    MenuPokedexScreen(
        innerPadding = PaddingValues(0.dp),
        onNavigateToDetail = {},
        onNavigateToHunt = {})
}

