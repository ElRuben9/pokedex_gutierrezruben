package gutierrez.ruben.pokedexcompose_gutierrrezruben.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.FavoritesRow
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.PokedexGrid
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList

@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues) {
    val favorites = pokemonList.filter { it.favorite }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 10.dp)
        )

        FavoritesRow(favoriteList = favorites)

        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 10.dp)
        )

        PokedexGrid(
            pokemonList = pokemonList,
            modifier = Modifier.weight(1f)
        )
    }
}