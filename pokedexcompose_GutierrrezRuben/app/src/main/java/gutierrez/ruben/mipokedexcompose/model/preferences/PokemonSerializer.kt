package gutierrez.ruben.mipokedexcompose.model.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import gutierrez.ruben.mipokedexcompose.R
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object PokemonSerializer: Serializer<Pokemon> {
    override val defaultValue: Pokemon = Pokemon(
        name = "",
        number = -1,
        type = "",
        description = "",
        height = 0f,
        weight = 0f,
        favorite = false,
        ability = "",
        image = R.drawable.wtp
    )

    override suspend fun readFrom(input: InputStream): Pokemon {
        return  try {
            Json.decodeFromString(
                Pokemon.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("No se pudo leer el JSON", exception)
        }
    }

    override suspend fun writeTo(
        t: Pokemon,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(Pokemon.serializer(), t)
                .encodeToByteArray()
        )
    }

}