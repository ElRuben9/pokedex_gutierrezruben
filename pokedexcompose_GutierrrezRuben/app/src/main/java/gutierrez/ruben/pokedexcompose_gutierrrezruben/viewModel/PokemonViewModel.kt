package gutierrez.ruben.pokedexcompose_gutierrrezruben.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList

class PokemonViewModel : ViewModel() {


    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemon by mutableStateOf(listOf<Pokemon>())
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    fun searchPokemon() {
        wildPokemon = pokemonList.random()
        gonePokemon = false
        captured = false
    }

    var captured by mutableStateOf(false)
        private set

    fun capturePokemon() {
        wildPokemon?.let {
            val isCaptured = (1..2).random()
            if (isCaptured == 1) {
                capturedPokemon = capturedPokemon + it
                gonePokemon = false
                wildPokemon = null
                captured = true

            } else {
                gonePokemon = true
                wildPokemon = null
                captured = false
            }

        }
    }
}
