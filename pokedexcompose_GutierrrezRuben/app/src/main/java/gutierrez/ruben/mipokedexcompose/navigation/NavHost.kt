package gutierrez.ruben.mipokedexcompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import gutierrez.ruben.mipokedexcompose.view.screens.AddPokemonScreen
import gutierrez.ruben.mipokedexcompose.view.screens.LoginScreen
import gutierrez.ruben.mipokedexcompose.view.screens.MenuPokedexScreen
import gutierrez.ruben.mipokedexcompose.view.screens.PokemonDetailScreen
import gutierrez.ruben.mipokedexcompose.view.screens.PokemonHuntScreen
import gutierrez.ruben.mipokedexcompose.view.screens.ProfileScreen
import gutierrez.ruben.mipokedexcompose.viewModel.AddPokemonViewModel
import gutierrez.ruben.mipokedexcompose.viewModel.PokemonDetailViewModel
import gutierrez.ruben.mipokedexcompose.viewModel.UserViewModel

@Composable
fun MyApp(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel,
    addPokemonViewModel: AddPokemonViewModel
) {
    val navController = rememberNavController()
    val onNavigateToDetail ={id: Int -> navController.navigate(route = PokemonDetail(id))}
    val onNavigateToMenu = {navController.navigate(PokemonList)}
    val onNavigateToProfile = {navController.navigate(Profile)}
    val onNavigateToAddPokemon = {navController.navigate(AddPokemon)}
    val onLogout = {
        userViewModel.logout()
        navController.navigate(Login) {
            popUpTo(0) { inclusive = true }
        }
    }

    NavHost(navController, startDestination = Login){
        composable<Login> {
            LoginScreen(innerPadding, onNavigateToMenu, userViewModel)
        }

        composable<PokemonList>{
            MenuPokedexScreen(
                innerPadding,
                onNavigateToDetail = onNavigateToDetail,
                onNavigateToHunt = { navController.navigate(route = PokemonHunt) },
                onNavigateToProfile = onNavigateToProfile,
                onNavigateToAddPokemon = onNavigateToAddPokemon,
                users = userViewModel.users,
                onLogout = onLogout
            )
        }
        composable<AddPokemon> {
            AddPokemonScreen(innerPadding, addPokemonViewModel)
        }
        composable<PokemonDetail>() { backStackEntry ->
            val pokemonDetail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(innerPadding, pokemonDetail.pokemon, pokemonDetailViewModel)
        }
        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding, onNavigateToDetail = onNavigateToDetail)
        }

        composable<Profile> {
            ProfileScreen(innerPadding, userViewModel)
        }
    }
}