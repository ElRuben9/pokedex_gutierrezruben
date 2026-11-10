package gutierrez.ruben.pokedexcompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import gutierrez.ruben.pokedexcompose.ui.screens.LoginScreen
import gutierrez.ruben.pokedexcompose.ui.screens.PokedexScreen
import gutierrez.ruben.pokedexcompose.ui.screens.PokemonDetailScreen
import gutierrez.ruben.pokedexcompose.ui.screens.PokemonHuntScreen
import gutierrez.ruben.pokedexcompose.ui.screens.ProfileScreen
import gutierrez.ruben.pokedexcompose.ui.viewmodel.LoginViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokedexViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonDetailViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonHuntViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.ProfileViewModel

const val LOGIN_ROUTE = "login"
const val POKEDEX_ROUTE = "pokedex"
const val HUNT_ROUTE = "captura"
const val PROFILE_ROUTE = "perfil"

private const val DETAIL_ARG = "numero"
private const val DETAIL_ROUTE = "detalle/{$DETAIL_ARG}"

fun detailRoute(number: Int): String = "detalle/$number"

@Composable
fun PokedexNavHost(
    innerPadding: PaddingValues,
    loginViewModel: LoginViewModel,
    pokedexViewModel: PokedexViewModel,
    detailViewModel: PokemonDetailViewModel,
    huntViewModel: PokemonHuntViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()

    val openDetail: (Int) -> Unit = { number ->
        navController.navigate(detailRoute(number))
    }

    NavHost(
        navController = navController,
        startDestination = LOGIN_ROUTE
    ) {
        composable(LOGIN_ROUTE) {
            LoginScreen(
                innerPadding = innerPadding,
                viewModel = loginViewModel,
                onLoggedIn = {
                    navController.navigate(POKEDEX_ROUTE) {
                        popUpTo(LOGIN_ROUTE) { inclusive = true }
                    }
                }
            )
        }

        composable(POKEDEX_ROUTE) {
            PokedexScreen(
                innerPadding = innerPadding,
                viewModel = pokedexViewModel,
                onPokemonClick = openDetail,
                onOpenHunt = { navController.navigate(HUNT_ROUTE) },
                onOpenProfile = { navController.navigate(PROFILE_ROUTE) },
                onLogout = {
                    loginViewModel.logout()
                    navController.navigate(LOGIN_ROUTE) {
                        popUpTo(POKEDEX_ROUTE) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = DETAIL_ROUTE,
            arguments = listOf(navArgument(DETAIL_ARG) { type = NavType.IntType })
        ) { backStackEntry ->
            val number = backStackEntry.arguments?.getInt(DETAIL_ARG)
            if (number != null) {
                PokemonDetailScreen(
                    innerPadding = innerPadding,
                    pokemonNumber = number,
                    viewModel = detailViewModel
                )
            }
        }

        composable(HUNT_ROUTE) {
            PokemonHuntScreen(
                innerPadding = innerPadding,
                viewModel = huntViewModel,
                onPokemonClick = openDetail
            )
        }

        composable(PROFILE_ROUTE) {
            ProfileScreen(
                innerPadding = innerPadding,
                viewModel = profileViewModel
            )
        }
    }
}
