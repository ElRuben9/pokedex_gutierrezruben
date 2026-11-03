package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.viewmodel

import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon

data class PokemonDetailState(
    val pokemon: Pokemon,
    val previous: Pokemon,
    val next: Pokemon,
)
