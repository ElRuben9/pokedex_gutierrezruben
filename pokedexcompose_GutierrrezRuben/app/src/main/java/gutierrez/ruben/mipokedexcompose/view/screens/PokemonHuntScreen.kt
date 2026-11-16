package gutierrez.ruben.mipokedexcompose.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gutierrez.ruben.mipokedexcompose.ui.theme.Ghost
import gutierrez.ruben.mipokedexcompose.ui.theme.Green
import gutierrez.ruben.mipokedexcompose.ui.theme.Pink40
import gutierrez.ruben.mipokedexcompose.ui.theme.Psych
import gutierrez.ruben.mipokedexcompose.ui.theme.Purple40
import gutierrez.ruben.mipokedexcompose.ui.theme.Purple80
import gutierrez.ruben.mipokedexcompose.ui.theme.Typography
import gutierrez.ruben.mipokedexcompose.view.components.PokemonRow
import gutierrez.ruben.mipokedexcompose.viewModel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel(), onNavigateToDetail: (id: Int) -> Unit) {
    Column(Modifier.fillMaxWidth().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
        Button({viewModel.searchPokemon()}) {
            Text("Buscar pokemon en la hierba")
        }
        viewModel.wildPokemon?.let {
                pokemon ->
            Image(painterResource((pokemon.image)), "${pokemon.name} image")
            Text("Aparecio un ${pokemon.name} salvaje",
            style = Typography.labelLarge,
            color = Purple40)
            Spacer(Modifier.size(15.dp))

            Button({ viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}")
            }
        }
        if(viewModel.gonePokemon) {
            Text("El pokemon se ha escapado 😓",
                style = Typography.labelLarge,
                color = Psych
            )
        }
        if (viewModel.isPokemonCaptured){
            Text("Has atrapado al pokemon 🥳",
                style = Typography.labelLarge,
                color = Green)
        }
        Spacer(Modifier.size(20.dp))
        Text("Pokemones capturados",
            style = Typography.titleLarge,
            color = Ghost)

        LazyColumn() {
            items(viewModel.capturedPokemon){
                pokemon->
                PokemonRow(pokemon, onNavigateToDetail)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHuntPreview(){
    PokemonHuntScreen(PaddingValues(15.dp), onNavigateToDetail = {} )
}