package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Login

@Serializable
data object PokemonList

@Serializable
data object PokemonHunt

@Serializable
data class PokemonDetail(val pokemon: Int)
