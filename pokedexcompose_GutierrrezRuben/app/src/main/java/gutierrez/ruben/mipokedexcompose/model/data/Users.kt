package gutierrez.ruben.mipokedexcompose.model.data

import android.content.Context
import gutierrez.ruben.mipokedexcompose.model.domain.User
import gutierrez.ruben.mipokedexcompose.model.preferences.ARCHIVO
import gutierrez.ruben.mipokedexcompose.model.preferences.POKEMON_DISPLAY
import gutierrez.ruben.mipokedexcompose.model.preferences.USER
import gutierrez.ruben.mipokedexcompose.model.preferences.USERNAME

class Users(context: Context) {
    val validUsers = listOf<User>(
        User("ruben.gutierrez@gmail.com", "123456", "Ruben Gutierrez")
    )

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun getUserByEmail(mail: String): User? {
        val users = validUsers.filter {
            it.email.equals(mail, true)
        }
        return if (users.isEmpty()) null else users.first()
    }

    fun loginService(user: String, password: String): User? {
        val matchingUser = getUserByEmail(user) ?: return null
        return matchingUser.takeIf { it.password == password }
    }

    fun saveUser(user: String, name: String) {
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun getCurrentUser(): User? {
        val user = shared.getString(USER, null) ?: return null
        return getUserByEmail(user)
    }

    fun savePokemonDisplay(useGrid: Boolean) {
        val user = shared.getString(USER, null) ?: return
        shared.edit()
            .putBoolean(pokemonDisplayKey(user), useGrid)
            .apply()
    }

    fun getPokemonDisplay(): Boolean {
        val user = shared.getString(USER, null) ?: return false
        return shared.getBoolean(pokemonDisplayKey(user), false)
    }

    private fun pokemonDisplayKey(user: String): String {
        return "${POKEMON_DISPLAY}_${user.trim().lowercase()}"
    }

    fun logout() {
        val editor = shared.edit()
        editor.putString(USER, null)
        editor.putString(USERNAME, null)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val user = shared.getString(USER, null)
        val username = shared.getString(USERNAME, null)

        if (user != null && username != null) {
            return true
        }
        return false
    }
}
