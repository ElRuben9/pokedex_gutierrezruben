package gutierrez.ruben.pokedexcompose_gutierrrezruben.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gutierrez.ruben.pokedexcompose_gutierrrezruben.components.PokemonRow
import gutierrez.ruben.pokedexcompose_gutierrrezruben.viewModel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel()) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { viewModel.searchPokemon() }) {
            Text("Buscar Pokemon en la Hierva")
        }
        Spacer(Modifier.size(18.dp))

        viewModel.wildPokemon?.let { pokemon ->
            Text("Aparecio un pokemon: ${pokemon.name} salvaje!")
            Image(
                painterResource(pokemon.image),
                modifier = Modifier
                    .height(90.dp)
                    .padding(0.dp, 15.dp),
                contentDescription = "${pokemon.name} image"
            )

            Spacer(Modifier.size(20.dp))

            viewModel.wildPokemon
                ?.let { }

            if (viewModel.gonePokemon) {
                Text("El pokemon se ha escapado")
            }

            if (viewModel.captured) {

            }

            Spacer(Modifier.size(20.dp))

            Text("")

            Button(onClick = { viewModel.capturePokemon() }) {
                Text("Capturar a ${pokemon.name}!")
            }
            LazyColumn() {
                items(viewModel.capturedPokemon) { pokemon ->
                    PokemonRow(pokemon)
                }
            }
        }

    }

}


@Preview
@Composable
fun previewPokemonHuntScreen() {
    PokemonHuntScreen(PaddingValues(15.dp))
}
