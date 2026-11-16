package gutierrez.ruben.mipokedexcompose.model.data.sqllite

import android.content.ContentValues
import android.database.Cursor
import gutierrez.ruben.mipokedexcompose.R
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon

class PokemonMapper {

    fun getPokemon(cursor: Cursor): Pokemon {
        val num = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NUMBER))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NAME))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_DESCRIPTION))
        val type = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_TYPE))

        return Pokemon(
            name = name,
            number = num,
            type = type,
            description = description,
            height = 0f,
            weight = 0f,
            favorite = false,
            ability = "",
            image = R.drawable.wtp
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues {
        val values = ContentValues().apply {
            put(Contract.Pokemons.COLUMN_NAME_NUMBER, pokemon.number)
            put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.name)
            put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.description)
            put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.type)
        }

        return values
    }
}
