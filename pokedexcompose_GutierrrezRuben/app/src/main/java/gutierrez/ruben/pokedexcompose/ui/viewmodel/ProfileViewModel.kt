package gutierrez.ruben.pokedexcompose.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.data.PokemonDataStore
import gutierrez.ruben.pokedexcompose.data.SessionDataStore
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class CompanionCard(
    val pokemon: Pokemon,
    val daysTogether: Long?
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val session = SessionDataStore(application)
    private val companions = PokemonDataStore(application)

    val name: String?
        get() = session.name()

    val email: String?
        get() = session.email()

    val companion: StateFlow<CompanionCard> = companions.companion
        .map { pokemon -> CompanionCard(pokemon, daysSince(pokemon.companionSince)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CompanionCard(Pokemon.None, null)
        )

    private fun daysSince(isoDate: String): Long? {
        if (isoDate.isBlank()) return null
        return runCatching {
            ChronoUnit.DAYS.between(LocalDate.parse(isoDate), LocalDate.now())
        }.getOrNull()
    }
}
