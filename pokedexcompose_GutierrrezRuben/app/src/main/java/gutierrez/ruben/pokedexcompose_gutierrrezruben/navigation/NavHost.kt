package gutierrez.ruben.pokedexcompose_gutierrrezruben.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import gutierrez.ruben.pokedexcompose_gutierrrezruben.screens.MenuPokedexScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.screens.PokemonDetailScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.screens.PokemonHuntScreen


@Composable
fun MyApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = PokemonList) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onNavigateToDetail = { pokemonNum -> navController.navigate(PokemonDetail(pokemonNum)) },
                onNavigateToHunt = { navController.navigate(PokemonHunt) }
            )
        }
        composable<PokemonDetail> {
            val args = it.toRoute<PokemonDetail>()
            PokemonDetailScreen(innerPadding, args.pokemon)
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding)
        }


    }
}


