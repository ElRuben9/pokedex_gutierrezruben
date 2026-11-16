package gutierrez.ruben.mipokedexcompose.view.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.mipokedexcompose.R
import gutierrez.ruben.mipokedexcompose.model.data.CompanionPreferences
import gutierrez.ruben.mipokedexcompose.model.data.absol
import gutierrez.ruben.mipokedexcompose.ui.theme.Grey
import gutierrez.ruben.mipokedexcompose.utilities.getColorByType
import gutierrez.ruben.mipokedexcompose.view.components.CuadroBlanco
import gutierrez.ruben.mipokedexcompose.viewModel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    innerPadding: PaddingValues,
    pokemonNumber: Int,
    pokemonDetailViewModel: PokemonDetailViewModel
) {
    val pokemon = pokemonDetailViewModel.getPokemon(pokemonNumber)
    val colors = getColorByType(pokemon.type)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.first)
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 25.dp)
        ) {
            Text(
                text = pokemon.name,
                color = colors.second,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "N° " + pokemon.number,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                color = Grey,
                fontSize = 20.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.estrella),
            contentDescription = "star",
            modifier = Modifier
                .padding(10.dp)
                .size(45.dp)
                .align(Alignment.TopEnd)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = "pokeball",
            modifier = Modifier
                .offset(x = 200.dp, y = 65.dp)
                .size(230.dp)
        )
        CuadroBlanco(pokemon, pokemonDetailViewModel)
        Image(
            painterResource(pokemon.image), contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp)
                .size(260.dp),
            Alignment.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    val viewModel = PokemonDetailViewModel(CompanionPreferences(LocalContext.current))
    PokemonDetailScreen(PaddingValues(10.dp, 15.dp), absol.number, viewModel)
}