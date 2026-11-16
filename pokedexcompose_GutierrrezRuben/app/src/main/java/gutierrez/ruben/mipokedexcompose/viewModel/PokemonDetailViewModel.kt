package gutierrez.ruben.mipokedexcompose.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gutierrez.ruben.mipokedexcompose.model.data.CompanionPreferences
import gutierrez.ruben.mipokedexcompose.model.data.getPokemonByNumber
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import gutierrez.ruben.mipokedexcompose.model.preferences.PokemonSerializer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val companionDataStore: CompanionPreferences): ViewModel() {
    var selectedCompanionNumber by mutableStateOf<Int?>(null)
        private set

    val companion: StateFlow<Pokemon> = companionDataStore.pokemonCompanion()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PokemonSerializer.defaultValue
        )

    fun getPokemon(number: Int): Pokemon = getPokemonByNumber(number)

    fun saveCompanion(pokemon: Pokemon) {
        viewModelScope.launch {
            companionDataStore.saveCompanion(pokemon)
            selectedCompanionNumber = pokemon.number
        }
    }
}