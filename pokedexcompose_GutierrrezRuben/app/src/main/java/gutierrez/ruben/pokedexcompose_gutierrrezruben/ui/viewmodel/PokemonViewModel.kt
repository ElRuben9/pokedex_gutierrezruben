package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import kotlin.random.Random

private const val CAPTURE_CHANCE = 0.5f

class PokemonViewModel(
    pokemons: List<Pokemon> = pokemonList,
    private val captureChance: Float = CAPTURE_CHANCE,
) : ViewModel() {

    private val _pokemons = pokemons.toMutableStateList()
    val pokemons: List<Pokemon> get() = _pokemons

    val favorites: List<Pokemon> by derivedStateOf { _pokemons.filter { it.favorite } }

    var pokedexState by mutableStateOf(PokedexState())
        private set

    fun detailOf(number: Int): PokemonDetailState? {
        val index = _pokemons.indexOfFirst { it.number == number }
        if (index == -1) return null
        return PokemonDetailState(
            pokemon = _pokemons[index],
            previous = _pokemons[(index - 1).mod(_pokemons.size)],
            next = _pokemons[(index + 1).mod(_pokemons.size)],
        )
    }

    fun toggleFavorite(number: Int) {
        val index = _pokemons.indexOfFirst { it.number == number }
        if (index == -1) return
        val pokemon = _pokemons[index]
        _pokemons[index] = pokemon.copy(favorite = !pokemon.favorite)
    }

    fun searchPokemon() {
        pokedexState = pokedexState.copy(
            wildPokemon = _pokemons.random(),
            lastCaptured = null,
            escaped = null,
        )
    }

    fun capturePokemon() {
        val wild = pokedexState.wildPokemon ?: return
        pokedexState = if (Random.nextFloat() < captureChance) {
            pokedexState.copy(
                wildPokemon = null,
                lastCaptured = wild,
                team = listOf(wild) + pokedexState.team,
            )
        } else {
            pokedexState.copy(wildPokemon = null, escaped = wild)
        }
    }
}
