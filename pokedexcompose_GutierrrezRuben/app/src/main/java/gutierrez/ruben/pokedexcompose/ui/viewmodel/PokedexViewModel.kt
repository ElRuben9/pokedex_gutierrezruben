package gutierrez.ruben.pokedexcompose.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import gutierrez.ruben.pokedexcompose.data.PokedexCatalog
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.data.SessionDataStore

class PokedexViewModel(application: Application) : AndroidViewModel(application) {

    private val session = SessionDataStore(application)

    val pokemons: List<Pokemon> = PokedexCatalog.pokemons

    val favorites: List<Pokemon> = pokemons.filter { it.favorite }

    var gridMode by mutableStateOf(session.isGridMode())
        private set

    fun toggleGridMode() {
        gridMode = !gridMode
        session.saveGridMode(gridMode)
    }
}
