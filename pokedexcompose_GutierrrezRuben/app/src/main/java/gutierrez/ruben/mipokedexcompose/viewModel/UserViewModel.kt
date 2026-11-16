package gutierrez.ruben.mipokedexcompose.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import gutierrez.ruben.mipokedexcompose.model.data.CompanionPreferences
import gutierrez.ruben.mipokedexcompose.model.data.Users
import gutierrez.ruben.mipokedexcompose.model.data.absol
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import gutierrez.ruben.mipokedexcompose.model.domain.User
import gutierrez.ruben.mipokedexcompose.model.preferences.PokemonSerializer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserViewModel(users: Users,
    private val companionDataStore: CompanionPreferences
    ): ViewModel() {

    var currentUser by mutableStateOf<User?>(null)
    init {
        currentUser = users.getCurrentUser()
    }
    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")

    val users = users

    val companion: StateFlow<Pokemon> = companionDataStore.pokemonCompanion()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PokemonSerializer.defaultValue
    )

    fun login(user: String, password: String) {
        val loggedUser = users.loginService(user, password)
        if (loggedUser == null){
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos."
            currentUser = null
        } else {
            loginError = false
            errorDescription = ""
            currentUser = loggedUser
            users.saveUser(loggedUser.email, loggedUser.name)
        }
    }

    fun logout() {
        users.logout()
        currentUser = null
        loginError = false
        errorDescription = ""
    }

    fun isLoggedIn(): Boolean {
        return currentUser != null || users.isLoggedIn()
    }
}