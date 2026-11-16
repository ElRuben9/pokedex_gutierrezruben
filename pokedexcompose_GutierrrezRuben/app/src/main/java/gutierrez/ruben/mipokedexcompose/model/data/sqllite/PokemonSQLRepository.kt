package gutierrez.ruben.mipokedexcompose.model.data.sqllite

import gutierrez.ruben.mipokedexcompose.model.data.interfaces.IPokemonDAO
import gutierrez.ruben.mipokedexcompose.model.data.interfaces.IPokemonRepository
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon

class PokemonSQLRepository(private val dao: IPokemonDAO) : IPokemonRepository {

    override suspend fun addPokemon(pokemon: Pokemon) {
        if (pokemon.name.isBlank() || pokemon.number <= 0) return
        dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemons(): List<Pokemon> {
        return dao.getAllPokemons()
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        return dao.getFavoritePokemons()
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return dao.getPokemonsByType(type)
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        if (pokemon.name.isBlank() || pokemon.number <= 0) return
        dao.updatePokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Int) {
        dao.getPokemonById(id)?.let { dao.deletePokemon(it) }
    }
}
