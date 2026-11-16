package gutierrez.ruben.mipokedexcompose.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gutierrez.ruben.mipokedexcompose.model.data.interfaces.IPokemonRepository
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import gutierrez.ruben.mipokedexcompose.model.domain.pokemonTypes
import gutierrez.ruben.mipokedexcompose.ui.theme.Typography
import gutierrez.ruben.mipokedexcompose.viewModel.AddPokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues,
    addPokemonViewModel: AddPokemonViewModel
) {
    var number = remember { TextFieldState() }
    var name = remember { TextFieldState() }
    var type = remember { TextFieldState(pokemonTypes[0]) }
    var description = remember { TextFieldState() }
    var openType by remember { mutableStateOf(false) }

    val pokemonList by addPokemonViewModel.pokemons.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        addPokemonViewModel.loadPokemons()
    }

    Column(
        Modifier
        .fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            "Agregar pokemon",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )
        Spacer(Modifier.size(20.dp))
        TextField(
            state = number,
            label = {
                Text("Numero de Pokemon")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            state = name,
            label = {
                Text("Nombre")
            }
        )
        ExposedDropdownMenuBox(
            expanded = openType,
            onExpandedChange = {openType = it}
        ) {
            OutlinedTextField(
                readOnly = true,
                label = {Text("Selecciona un pokemon")},
                state = type,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType) },
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )
            ExposedDropdownMenu(
                expanded = openType,
                onDismissRequest = {openType = false}
            ) {
                pokemonTypes.forEach {
                    t ->
                    DropdownMenuItem(
                        text = {Text(t)},
                        onClick = {
                            type.setTextAndPlaceCursorAtEnd(t)
                            openType = false
                        }
                    )
                }
            }
        }
        TextField(
            state = description,
            label = {
                Text("Descripcion")
            }
        )

        Button(
            onClick = {
                val pokemonNumber = number.text.toString().toIntOrNull()
                if (pokemonNumber != null && name.text.isNotBlank()) {
                    addPokemonViewModel.savePokemon(
                        name = name.text.toString(),
                        type = type.text.toString(),
                        number = pokemonNumber,
                        description = description.text.toString()
                    )
                }
            }
        ) {
            Text("Guardar Pokemon")
        }

        if (addPokemonViewModel.pokemonSaved) {
            Text(
                "Pokemon guardado correctamente",
                color = Color.Green
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(pokemonList) { pokemon ->
                Text(
                    text = pokemon.toString(),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPokemonScreenPreview() {
    AddPokemonScreen(
        PaddingValues(20.dp),
        AddPokemonViewModel(object : IPokemonRepository {
            override suspend fun addPokemon(pokemon: Pokemon) {}
            override suspend fun getPokemons(): List<Pokemon> = emptyList()
            override suspend fun getFavoritePokemons(): List<Pokemon> = emptyList()
            override suspend fun getPokemonsByType(type: String): List<Pokemon> = emptyList()
            override suspend fun updatePokemon(pokemon: Pokemon) {}
            override suspend fun deletePokemon(id: Int) {}
        })
    )
}
