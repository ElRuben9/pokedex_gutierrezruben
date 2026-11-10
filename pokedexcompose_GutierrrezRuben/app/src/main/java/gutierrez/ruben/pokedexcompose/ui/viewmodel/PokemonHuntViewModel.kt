package gutierrez.ruben.pokedexcompose.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import gutierrez.ruben.pokedexcompose.data.PokedexCatalog
import gutierrez.ruben.pokedexcompose.data.Pokemon

class PokemonHuntViewModel : ViewModel() {

    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemons by mutableStateOf<List<Pokemon>>(emptyList())
        private set

    var lastCatchSucceeded by mutableStateOf<Boolean?>(null)
        private set

    fun search() {
        wildPokemon = PokedexCatalog.pokemons.random()
        lastCatchSucceeded = null
    }

    fun capture() {
        val wild = wildPokemon ?: return
        val succeeded = (0..1).random() == 1

        if (succeeded) {
            capturedPokemons = capturedPokemons + wild
        }

        lastCatchSucceeded = succeeded
        wildPokemon = null
    }
}
