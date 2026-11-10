package gutierrez.ruben.pokedexcompose.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import gutierrez.ruben.pokedexcompose.data.SessionDataStore

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val session = SessionDataStore(application)

    private val accountEmail = "ruben.gutierrez@correo.com"
    private val accountPassword = "123456"
    private val accountName = "Ruben Gutierrez"

    var uiState by mutableStateOf(LoginUiState(isLoggedIn = session.isLoggedIn()))
        private set

    fun onEmailChange(value: String) {
        uiState = uiState.copy(email = value, error = null)
    }

    fun onPasswordChange(value: String) {
        uiState = uiState.copy(password = value, error = null)
    }

    fun submit() {
        if (uiState.email.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(error = "Escribe tu correo y tu contraseña.")
            return
        }

        val validEmail = uiState.email.trim().equals(accountEmail, ignoreCase = true)

        uiState = if (validEmail && uiState.password == accountPassword) {
            session.saveSession(accountEmail, accountName)
            uiState.copy(error = null, isLoggedIn = true)
        } else {
            uiState.copy(error = "Correo o contraseña incorrectos.")
        }
    }

    fun logout() {
        session.clearSession()
        uiState = LoginUiState()
    }
}
