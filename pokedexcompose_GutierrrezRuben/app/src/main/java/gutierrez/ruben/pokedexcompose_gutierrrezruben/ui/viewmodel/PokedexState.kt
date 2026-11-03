package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.viewmodel

import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon

data class PokedexState(
    val wildPokemon: Pokemon? = null,
    val lastCaptured: Pokemon? = null,
    val escaped: Pokemon? = null,
    val team: List<Pokemon> = emptyList(),
)
