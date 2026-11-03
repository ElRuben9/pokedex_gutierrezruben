package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dimens
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.MiPokedexTheme

private const val GRID_COLUMNS = 3

@Composable
fun MenuPokedex(
    pokemonList: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit,
    bottomPadding: Dp = 0.dp,
) {
    LazyColumn(contentPadding = PaddingValues(bottom = bottomPadding)) {
        items(pokemonList, key = { it.number }) { pokemon ->
            PokemonRow(pokemon, onClick = { onPokemonClick(pokemon) })
        }
    }
}

@Composable
fun FavoritesRow(favoriteList: List<Pokemon>, onPokemonClick: (Pokemon) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = Dimens.MarginMedium),
        horizontalArrangement = Arrangement.spacedBy(Dimens.MarginMedium),
    ) {
        items(favoriteList, key = { it.number }) { pokemon ->
            FavoritePokemon(pokemon, onClick = { onPokemonClick(pokemon) })
        }
    }
}

@Composable
fun PokedexGrid(
    pokemonList: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit,
    bottomPadding: Dp = 0.dp,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COLUMNS),
        contentPadding = PaddingValues(
            start = Dimens.GridPaddingHorizontal,
            top = Dimens.GridPaddingVertical,
            end = Dimens.GridPaddingHorizontal,
            bottom = Dimens.GridPaddingVertical + bottomPadding,
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.GridSpacingVertical),
        horizontalArrangement = Arrangement.spacedBy(Dimens.GridSpacingHorizontal),
    ) {
        items(pokemonList, key = { it.number }) { pokemon ->
            PokemonCell(pokemon, onClick = { onPokemonClick(pokemon) })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuPokedexPreview() {
    MiPokedexTheme {
        MenuPokedex(pokemonList, onPokemonClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesRowPreview() {
    MiPokedexTheme {
        FavoritesRow(pokemonList.filter { it.favorite }, onPokemonClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PokedexGridPreview() {
    MiPokedexTheme {
        PokedexGrid(pokemonList, onPokemonClick = {})
    }
}
