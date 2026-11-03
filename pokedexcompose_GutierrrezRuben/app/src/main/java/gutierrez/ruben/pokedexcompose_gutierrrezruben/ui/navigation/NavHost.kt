package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.screens.LoginScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.screens.MenuPokedexScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.screens.PokemonDetailScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.screens.PokemonHuntScreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.viewmodel.PokemonViewModel
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.viewmodel.UserViewModel

@Composable
fun PokedexNavHost(
    innerPadding: PaddingValues,
    viewModel: PokemonViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory),
) {
    val navController = rememberNavController()
    val openDetail = { pokemon: Pokemon ->
        navController.navigate(PokemonDetail(pokemon.number)) { launchSingleTop = true }
    }
    val openMenu = {
        navController.navigate(PokemonList) {
            popUpTo<Login> { inclusive = true }
            launchSingleTop = true
        }
    }
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            val isLoggedIn = remember { userViewModel.isLoggedIn() }
            LoginScreen(
                isLoggedIn = isLoggedIn,
                loginError = userViewModel.loginError,
                errorDescription = userViewModel.errorDescription,
                onLoginClick = { mail, password ->
                    userViewModel.login(mail, password)
                    if (!userViewModel.loginError) openMenu()
                },
                onLoggedIn = openMenu,
            )
        }
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                pokemonList = viewModel.pokemons,
                favoriteList = viewModel.favorites,
                grid = userViewModel.gridDisplay,
                onGridChange = userViewModel::changeDisplay,
                onPokemonClick = openDetail,
                onHuntClick = { navController.navigate(PokemonHunt) { launchSingleTop = true } },
                onLogoutClick = {
                    userViewModel.logout()
                    navController.navigate(Login) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
            )
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(
                innerPadding = innerPadding,
                state = viewModel.pokedexState,
                onSearchClick = viewModel::searchPokemon,
                onCaptureClick = viewModel::capturePokemon,
                onPokemonClick = openDetail,
            )
        }
        composable<PokemonDetail> { backStackEntry ->
            val number = backStackEntry.toRoute<PokemonDetail>().pokemon
            PokemonDetailScreen(
                detail = viewModel.detailOf(number),
                onFavoriteClick = { viewModel.toggleFavorite(number) },
                onNeighborClick = { neighbor ->
                    navController.navigate(PokemonDetail(neighbor.number)) {
                        popUpTo<PokemonDetail> { inclusive = true }
                    }
                },
            )
        }
    }
}
