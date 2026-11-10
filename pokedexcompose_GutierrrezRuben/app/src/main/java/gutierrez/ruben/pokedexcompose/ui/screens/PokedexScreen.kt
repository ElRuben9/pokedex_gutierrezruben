package gutierrez.ruben.pokedexcompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose.R
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.ui.theme.Blue
import gutierrez.ruben.pokedexcompose.ui.theme.Green
import gutierrez.ruben.pokedexcompose.ui.theme.LightBlue
import gutierrez.ruben.pokedexcompose.ui.theme.LightGreen
import gutierrez.ruben.pokedexcompose.ui.theme.OffWhite
import gutierrez.ruben.pokedexcompose.ui.theme.Purple40
import gutierrez.ruben.pokedexcompose.ui.theme.TypePalette
import gutierrez.ruben.pokedexcompose.ui.theme.Typography
import gutierrez.ruben.pokedexcompose.ui.theme.paletteFor
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokedexViewModel

@Composable
fun PokedexScreen(
    innerPadding: PaddingValues,
    viewModel: PokedexViewModel,
    onPokemonClick: (Int) -> Unit,
    onOpenHunt: () -> Unit,
    onOpenProfile: () -> Unit,
    onLogout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            PokedexHeader(
                onOpenProfile = onOpenProfile,
                onLogout = onLogout
            )

            if (viewModel.favorites.isNotEmpty()) {
                SectionTitle("Favoritos")
                FavoritesStrip(
                    pokemons = viewModel.favorites,
                    onPokemonClick = onPokemonClick
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Todos los Pokémon", style = Typography.titleLarge)
                Spacer(Modifier.weight(1f))
                DisplayModeSwitch(
                    gridMode = viewModel.gridMode,
                    onToggle = viewModel::toggleGridMode
                )
            }

            if (viewModel.gridMode) {
                PokemonGrid(
                    pokemons = viewModel.pokemons,
                    onPokemonClick = onPokemonClick,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                PokemonList(
                    pokemons = viewModel.pokemons,
                    onPokemonClick = onPokemonClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        FloatingActionButton(
            onClick = onOpenHunt,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Purple40,
            contentColor = Color.White
        ) {
            Text(
                text = "Capturar",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun PokedexHeader(
    onOpenProfile: () -> Unit,
    onLogout: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 16.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onOpenProfile) {
            Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = "Perfil"
            )
        }

        Text(
            text = "Pokédex",
            style = Typography.titleLarge,
            color = Purple40
        )

        Spacer(Modifier.weight(1f))

        OutlinedButton(onClick = onLogout) {
            Text("Salir")
        }
    }
}

@Composable
private fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.padding(start = 16.dp, top = 12.dp),
        style = Typography.titleLarge
    )
}

@Composable
private fun DisplayModeSwitch(
    gridMode: Boolean,
    onToggle: () -> Unit
) {
    Switch(
        checked = gridMode,
        onCheckedChange = { onToggle() },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Green,
            checkedTrackColor = LightGreen,
            uncheckedThumbColor = Blue,
            uncheckedTrackColor = LightBlue,
            uncheckedBorderColor = Color.Transparent
        ),
        thumbContent = {
            Icon(
                painter = painterResource(
                    if (gridMode) R.drawable.grid_icon else R.drawable.list_icon
                ),
                contentDescription = if (gridMode) {
                    "Vista de cuadrícula"
                } else {
                    "Vista de lista"
                },
                modifier = Modifier.size(SwitchDefaults.IconSize)
            )
        }
    )
}

@Composable
private fun PokemonList(
    pokemons: List<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonRowItem(pokemon, onPokemonClick)
        }
    }
}

@Composable
private fun PokemonGrid(
    pokemons: List<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonGridItem(pokemon, onPokemonClick)
        }
    }
}

@Composable
private fun FavoritesStrip(
    pokemons: List<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonFavoriteItem(pokemon, onPokemonClick)
        }
    }
}

@Composable
private fun PokemonRowItem(
    pokemon: Pokemon,
    onPokemonClick: (Int) -> Unit
) {
    val palette = paletteFor(pokemon.type)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onPokemonClick(pokemon.number) }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier.size(80.dp)
        )

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = pokemon.name, style = Typography.labelLarge)
            Text(
                text = pokemon.description,
                fontSize = 10.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Altura: ${pokemon.height} m", style = Typography.labelMedium)
                Text(text = "Peso: ${pokemon.weight} kg", style = Typography.labelMedium)
            }
        }

        NumberChip(number = pokemon.number.toString(), palette = palette)
    }
}

@Composable
private fun PokemonGridItem(
    pokemon: Pokemon,
    onPokemonClick: (Int) -> Unit
) {
    val palette = paletteFor(pokemon.type)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onPokemonClick(pokemon.number) }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(id = pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit
            )
            NumberChip(number = pokemon.number.toString(), palette = palette)
        }
        Text(
            text = pokemon.name,
            style = Typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PokemonFavoriteItem(
    pokemon: Pokemon,
    onPokemonClick: (Int) -> Unit
) {
    val palette = paletteFor(pokemon.type)

    Column(
        modifier = Modifier
            .width(140.dp)
            .clickable { onPokemonClick(pokemon.number) }
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier.border(
                    border = BorderStroke(
                        width = 4.dp,
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                palette.background,
                                OffWhite,
                                palette.background,
                                OffWhite,
                                palette.background
                            )
                        )
                    )
                )
            ) {
                Image(
                    painter = painterResource(id = pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(4.dp)
                )
            }
            NumberChip(
                number = pokemon.number.toString(),
                palette = palette,
                modifier = Modifier.offset(x = 8.dp, y = 8.dp)
            )
        }

        Text(
            text = pokemon.name,
            style = Typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NumberChip(
    number: String,
    palette: TypePalette,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(5.dp)
            .size(30.dp)
            .clip(CircleShape)
            .background(color = palette.background),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = number,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            color = palette.content
        )
    }
}
