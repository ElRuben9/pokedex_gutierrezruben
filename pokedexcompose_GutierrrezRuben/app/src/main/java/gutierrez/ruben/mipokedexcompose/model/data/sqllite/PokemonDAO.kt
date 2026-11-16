package gutierrez.ruben.mipokedexcompose.model.data.sqllite

import gutierrez.ruben.mipokedexcompose.model.data.interfaces.IPokemonDAO
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDAO(private val helper: DatabaseHelper) : IPokemonDAO {

    private val mapper = PokemonMapper()

    override suspend fun insertPokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.insert(Contract.Pokemons.TABLE_NAME, null, mapper.getContentValues(pokemon))
            db.close()
        }
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME}"

            val cursor = db.rawQuery(query, null)
            cursor.use { c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(c))
                    }
                }
            }
        }
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val cursor = db.query(
                Contract.Pokemons.TABLE_NAME,
                null,
                "${Contract.Pokemons.COLUMN_ID} = ?",
                arrayOf(id.toString()),
                null,
                null,
                null
            )

            cursor.use { c ->
                if (c.moveToFirst()) mapper.getPokemon(c) else null
            }
        }
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        TODO("Pendiente de implementar en la siguiente práctica")
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val cursor = db.query(
                Contract.Pokemons.TABLE_NAME,
                null,
                "${Contract.Pokemons.COLUMN_NAME_TYPE} = ?",
                arrayOf(type),
                null,
                null,
                null
            )

            cursor.use { c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(c))
                    }
                }
            }
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.update(
                Contract.Pokemons.TABLE_NAME,
                mapper.getContentValues(pokemon),
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                arrayOf(pokemon.number.toString())
            )
            db.close()
        }
    }

    override suspend fun deletePokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.delete(
                Contract.Pokemons.TABLE_NAME,
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                arrayOf(pokemon.number.toString())
            )
            db.close()
        }
    }
}
