package gutierrez.ruben.pokedexcompose_gutierrrezruben.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import gutierrez.ruben.pokedexcompose_gutierrrezruben.screens.MenuPokedexScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.screens.PokemonDetailScreen

@Composable
fun PokedexNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PokemonList
    ) {
        composable<PokemonList> {
            MenuPokedexScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate(PokemonDetail(pokemon = pokemonId))
                }
            )
        }

        composable<PokemonDetail> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(pokemonNumber = detailRoute.pokemon)
        }
    }
}