package gutierrez.ruben.mipokedexcompose.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gutierrez.ruben.mipokedexcompose.model.data.pokemonList
import gutierrez.ruben.mipokedexcompose.navigation.PokemonDetail

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, onNavigateToDetail: (id: Int) -> Unit){
    LazyColumn() {
        items(pokemonList){ pokemon ->
            PokemonRow(pokemon, onNavigateToDetail)

        }
    }
}

@Composable
fun FavoritesRow(favoritesList: List<Pokemon>, onNavigateToDetail: (id: Int) -> Unit){
    LazyRow() {
        items(favoritesList){pokemon ->
            FavoritePokemon(pokemon, onNavigateToDetail)

        }
    }
}

@Composable
fun PokedexGrid(pokemonList: List<Pokemon>, onNavigateToDetail: (id: Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp, 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(pokemonList) {
            pokemon -> PokemonCell(pokemon, onNavigateToDetail)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MenuPokedexPreview() {
//    PokedexGrid(pokemonList, onNavigateToDetail = PokemonDetail)
//}