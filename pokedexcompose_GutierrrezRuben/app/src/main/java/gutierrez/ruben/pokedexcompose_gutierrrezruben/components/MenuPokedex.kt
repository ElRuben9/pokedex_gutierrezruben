package gutierrez.ruben.pokedexcompose_gutierrrezruben.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon


@Composable
fun MenuPokedex(pokemonList: List<Pokemon>) {
    LazyColumn() {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Composable
fun FavriteRow(favoriteList: List<Pokemon>, onNavigateToDetail: (Int) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(favoriteList) { pokemon ->
            FavoritePokemon(pokemon, onNavigateToDetail)
        }
    }
}

@Composable
fun PokedexGrid(pokemonList: List<Pokemon>, onNavigateToDetail: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonCell(pokemon, onNavigateToDetail)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexPreview() {
    MenuPokedex(pokemonList = pokemonList)
}

@Preview(showBackground = true)
@Composable
fun FavriteRowPreview() {
    FavriteRow(favoriteList = pokemonList.take(5), onNavigateToDetail = {})
}

@Preview(showBackground = true)
@Composable
fun PokedexGridPreview() {
    PokedexGrid(pokemonList = pokemonList, onNavigateToDetail = {})
}