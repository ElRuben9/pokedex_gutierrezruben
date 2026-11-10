package gutierrez.ruben.pokedexcompose.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import java.io.InputStream
import java.io.OutputStream
import java.time.LocalDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

private const val COMPANION_FILE = "companero.json"

private object PokemonSerializer : Serializer<Pokemon> {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    override val defaultValue: Pokemon = Pokemon.None

    override suspend fun readFrom(input: InputStream): Pokemon =
        try {
            json.decodeFromString(Pokemon.serializer(), input.readBytes().decodeToString())
        } catch (error: SerializationException) {
            throw CorruptionException("No se pudo leer el compañero guardado", error)
        }

    override suspend fun writeTo(t: Pokemon, output: OutputStream) {
        val encoded = json.encodeToString(Pokemon.serializer(), t)
        output.write(encoded.encodeToByteArray())
    }
}

private val Context.companionStore: DataStore<Pokemon> by dataStore(
    fileName = COMPANION_FILE,
    serializer = PokemonSerializer,
    scope = CoroutineScope(Dispatchers.IO)
)

class PokemonDataStore(context: Context) {

    private val store = context.applicationContext.companionStore

    val companion: Flow<Pokemon> = store.data.distinctUntilChanged()

    suspend fun save(pokemon: Pokemon) {
        store.updateData { stored ->
            val isSameCompanion = stored.number == pokemon.number
            pokemon.copy(
                companionSince = if (isSameCompanion) {
                    stored.companionSince
                } else {
                    LocalDate.now().toString()
                }
            )
        }
    }

    suspend fun clear() {
        store.updateData { Pokemon.None }
    }
}
