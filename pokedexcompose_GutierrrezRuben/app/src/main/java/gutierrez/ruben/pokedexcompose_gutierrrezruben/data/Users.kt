package gutierrez.ruben.pokedexcompose_gutierrrezruben.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.preferences.ARCHIVO
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.preferences.POKEMON_DISPLAY
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.preferences.USER
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.preferences.USERNAME
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.User

class Users(context: Context) {

    private val userList = listOf(
        User("ash@pokedex.com", "pikachu", "Ash"),
        User("misty@pokedex.com", "starmie", "Misty"),
        User("brock@pokedex.com", "onix", "Brock"),
    )

    private val shared: SharedPreferences =
        context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun getUserByMail(mail: String): User? =
        userList.find { it.email.equals(mail.trim(), ignoreCase = true) }

    fun loginService(email: String, password: String): User? {
        val user = getUserByMail(email)?.takeIf { it.password == password } ?: return null
        saveUser(user.email, user.name)
        return user
    }

    fun saveUser(email: String, name: String) {
        shared.edit {
            putString(USER, email)
            putString(USERNAME, name)
        }
    }
    fun isLoggedIn(): Boolean =
        shared.getString(USER, null) != null && shared.getString(USERNAME, null) != null
    fun logout() {
        shared.edit {
            remove(USER)
            remove(USERNAME)
        }
    }

    fun isGridDisplay(): Boolean = shared.getBoolean(POKEMON_DISPLAY, false)

    fun saveGridDisplay(grid: Boolean) {
        shared.edit { putBoolean(POKEMON_DISPLAY, grid) }
    }
}
