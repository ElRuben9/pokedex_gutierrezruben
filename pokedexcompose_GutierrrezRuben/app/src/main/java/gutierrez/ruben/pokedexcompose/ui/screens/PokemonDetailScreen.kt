package gutierrez.ruben.pokedexcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose.R
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.ui.components.PokemonDetailCard
import gutierrez.ruben.pokedexcompose.ui.theme.Grey
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonDetailUiState
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonDetailViewModel
import gutierrez.ruben.pokedexcompose.ui.theme.paletteFor
import kotlinx.coroutines.delay

@Composable
fun PokemonDetailScreen(
    innerPadding: PaddingValues,
    pokemonNumber: Int,
    viewModel: PokemonDetailViewModel
) {
    LaunchedEffect(pokemonNumber) {
        viewModel.open(pokemonNumber)
    }

    val uiState by viewModel.uiState.collectAsState()
    val notice by viewModel.notice.collectAsState()

    LaunchedEffect(notice) {
        if (notice != null) {
            delay(3_000)
            viewModel.dismissNotice()
        }
    }

    when (val state = uiState) {
        PokemonDetailUiState.Loading -> DetailPlaceholder(
            innerPadding = innerPadding,
            message = "Cargando Pokémon…"
        )

        PokemonDetailUiState.NotFound -> DetailPlaceholder(
            innerPadding = innerPadding,
            message = "No encontramos ese Pokémon."
        )

        is PokemonDetailUiState.Ready -> PokemonDetailContent(
            innerPadding = innerPadding,
            pokemon = state.pokemon,
            isCompanion = state.isCompanion,
            notice = notice,
            onChooseCompanion = viewModel::chooseAsCompanion
        )
    }
}

@Composable
private fun PokemonDetailContent(
    innerPadding: PaddingValues,
    pokemon: Pokemon,
    isCompanion: Boolean,
    notice: String?,
    onChooseCompanion: () -> Unit
) {
    val palette = paletteFor(pokemon.type)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = palette.background)
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier.padding(start = 25.dp)) {
            Text(
                text = pokemon.name,
                color = palette.content,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "N.º ${pokemon.number}",
                modifier = Modifier.padding(horizontal = 20.dp),
                color = Grey,
                fontSize = 20.sp
            )
        }

        if (isCompanion) {
            Image(
                painter = painterResource(R.drawable.estrella),
                contentDescription = "Pokémon compañero",
                modifier = Modifier
                    .padding(10.dp)
                    .size(45.dp)
                    .align(Alignment.TopEnd)
            )
        }

        Image(
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription = "pokeball",
            modifier = Modifier
                .offset(x = 200.dp, y = 65.dp)
                .size(230.dp)
        )

        PokemonDetailCard(
            pokemon = pokemon,
            isCompanion = isCompanion,
            notice = notice,
            onChooseCompanion = onChooseCompanion,
            modifier = Modifier.padding(top = 200.dp, bottom = 20.dp)
        )

        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            alignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp)
                .size(260.dp)
        )
    }
}

@Composable
private fun DetailPlaceholder(innerPadding: PaddingValues, message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Grey,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}
