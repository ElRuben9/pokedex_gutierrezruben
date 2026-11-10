package gutierrez.ruben.pokedexcompose.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import gutierrez.ruben.pokedexcompose.data.PokedexCatalog
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.data.PokemonDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface PokemonDetailUiState {
    data object Loading : PokemonDetailUiState
    data object NotFound : PokemonDetailUiState

    data class Ready(
        val pokemon: Pokemon,
        val isCompanion: Boolean
    ) : PokemonDetailUiState
}

class PokemonDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val companions = PokemonDataStore(application)

    private val requestedNumber = MutableStateFlow<Int?>(null)

    private val _notice = MutableStateFlow<String?>(null)
    val notice: StateFlow<String?> = _notice.asStateFlow()

    val uiState: StateFlow<PokemonDetailUiState> =
        combine(requestedNumber, companions.companion) { number, companion ->
            if (number == null) {
                PokemonDetailUiState.Loading
            } else {
                val pokemon = PokedexCatalog.pokemons.firstOrNull { it.number == number }
                if (pokemon == null) {
                    PokemonDetailUiState.NotFound
                } else {
                    PokemonDetailUiState.Ready(
                        pokemon = pokemon,
                        isCompanion = companion.number == pokemon.number
                    )
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PokemonDetailUiState.Loading
        )

    fun open(number: Int) {
        _notice.value = null
        if (requestedNumber.value == number) return
        requestedNumber.value = number
    }

    fun chooseAsCompanion() {
        val ready = uiState.value as? PokemonDetailUiState.Ready ?: return

        viewModelScope.launch {
            companions.save(ready.pokemon)
            _notice.value = "Compañero seleccionado."
        }
    }

    fun dismissNotice() {
        _notice.value = null
    }
}
