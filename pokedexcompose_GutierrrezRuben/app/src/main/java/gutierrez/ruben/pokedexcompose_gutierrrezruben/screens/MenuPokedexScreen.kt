package gutierrez.ruben.pokedexcompose_gutierrrezruben.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.R
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.FavoritesRow
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.MenuPokedex
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.PokedexGrid
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Blue
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Green
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.LightBlue
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.LightGreen

@Composable
fun MenuPokedexScreen(
    onPokemonClick: (Int) -> Unit
) {
    var grid by remember { mutableStateOf(false) }
    val favorites = remember { pokemonList.filter { it.favorite } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FavoritesRow(
            favoriteList = favorites,
            onPokemonClick = onPokemonClick
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Todos mis pokemones",
                style = MaterialTheme.typography.labelLarge
            )

            Switch(
                checked = grid,
                onCheckedChange = { grid = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Green,
                    checkedTrackColor = LightGreen,
                    uncheckedThumbColor = Blue,
                    uncheckedTrackColor = LightBlue,
                    uncheckedBorderColor = Color.Transparent
                ),
                thumbContent = if (grid) {
                    {
                        Icon(
                            painter = painterResource(R.drawable.grid),
                            contentDescription = "grid icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                } else {
                    {
                        Icon(
                            painter = painterResource(R.drawable.list),
                            contentDescription = "list icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                }
            )
        }

        Spacer(Modifier.height(8.dp))

        if (grid) {
            PokedexGrid(
                pokemonList = pokemonList,
                modifier = Modifier.weight(1f),
                onPokemonClick = onPokemonClick
            )
        } else {
            MenuPokedex(
                pokemonList = pokemonList,
                modifier = Modifier.weight(1f),
                onPokemonClick = onPokemonClick
            )
        }
    }
}