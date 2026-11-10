package gutierrez.ruben.pokedexcompose.data

import android.content.Context

private const val SESSION_FILE = "sesion_entrenador"
private const val KEY_EMAIL = "email"
private const val KEY_NAME = "nombre"
private const val KEY_GRID = "vista_cuadricula"

class SessionDataStore(context: Context) {

    private val preferences =
        context.applicationContext.getSharedPreferences(SESSION_FILE, Context.MODE_PRIVATE)

    fun saveSession(email: String, name: String) {
        preferences.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_NAME, name)
            .apply()
    }

    fun email(): String? = preferences.getString(KEY_EMAIL, null)

    fun name(): String? = preferences.getString(KEY_NAME, null)

    fun isLoggedIn(): Boolean = email() != null && name() != null

    fun clearSession() {
        preferences.edit()
            .remove(KEY_EMAIL)
            .remove(KEY_NAME)
            .apply()
    }

    fun isGridMode(): Boolean = preferences.getBoolean(KEY_GRID, false)

    fun saveGridMode(enabled: Boolean) {
        preferences.edit().putBoolean(KEY_GRID, enabled).apply()
    }
}
