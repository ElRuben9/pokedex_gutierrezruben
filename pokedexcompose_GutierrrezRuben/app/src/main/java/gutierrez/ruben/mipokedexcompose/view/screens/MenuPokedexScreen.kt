package gutierrez.ruben.mipokedexcompose.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.mipokedexcompose.R
import gutierrez.ruben.mipokedexcompose.model.data.Users
import gutierrez.ruben.mipokedexcompose.model.data.getFavoritePokemons
import gutierrez.ruben.mipokedexcompose.view.components.FavoritesRow
import gutierrez.ruben.mipokedexcompose.model.data.pokemonList
import gutierrez.ruben.mipokedexcompose.ui.theme.Blue
import gutierrez.ruben.mipokedexcompose.ui.theme.Green
import gutierrez.ruben.mipokedexcompose.ui.theme.LightBlue
import gutierrez.ruben.mipokedexcompose.ui.theme.LightGreen
import gutierrez.ruben.mipokedexcompose.ui.theme.Purple40
import gutierrez.ruben.mipokedexcompose.view.components.PokemonCell
import gutierrez.ruben.mipokedexcompose.view.components.PokemonRow

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onNavigateToDetail: (id: Int) -> Unit,
    onNavigateToHunt: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToAddPokemon: () -> Unit = {},
    users: Users? = null,
    onLogout: () -> Unit = {}
) {
    var grid by remember { mutableStateOf(users?.getPokemonDisplay() ?: false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onNavigateToProfile) {
                        Image(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = "Perfil"
                        )
                    }
                    IconButton(onClick = onNavigateToAddPokemon) {
                        Text(
                            text = "+",
                            color = Purple40,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Favorite Pokemon",
                    modifier = Modifier.padding(start = 16.dp, top = 10.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                OutlinedButton(
                    onClick = onLogout,
                    modifier = Modifier.padding(end = 16.dp, top = 10.dp)
                ) {
                    Text("Log out")
                }
            }

            FavoritesRow(getFavoritePokemons(), onNavigateToDetail)

            Spacer(Modifier.size(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("All Pokemons")

                Switch(
                    checked = grid,
                    onCheckedChange = {
                        grid = it
                        users?.savePokemonDisplay(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Green,
                        checkedTrackColor = LightGreen,
                        uncheckedThumbColor = Blue,
                        uncheckedTrackColor = LightBlue,
                        uncheckedBorderColor = Transparent
                    ),
                    thumbContent = if (grid) {
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.grid_icon),
                                contentDescription = "grid icon",
                                modifier = Modifier.size(SwitchDefaults.IconSize)
                            )
                        }
                    } else {
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.list_icon),
                                contentDescription = "list icon",
                                modifier = Modifier.size(SwitchDefaults.IconSize)
                            )
                        }
                    }
                )
            }
            if (grid) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(pokemonList) { pokemon ->
                        PokemonCell(
                            pokemon = pokemon,
                            onNavigateToDetail = onNavigateToDetail
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(pokemonList) { pokemon ->
                        PokemonRow(
                            pokemon = pokemon,
                            onNavigateToDetail = onNavigateToDetail
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onNavigateToHunt,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Purple40
        ) {
            Text(
                "Catch new",
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp),
                color = Color.White
                )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuPokedexScreenPreview() {
    MenuPokedexScreen(PaddingValues(10.dp, 15.dp), {}, {}, {})
}