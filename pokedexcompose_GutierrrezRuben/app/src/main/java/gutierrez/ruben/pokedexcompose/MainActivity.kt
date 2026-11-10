package gutierrez.ruben.pokedexcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import gutierrez.ruben.pokedexcompose.navigation.PokedexNavHost
import gutierrez.ruben.pokedexcompose.ui.theme.PokedexTheme
import gutierrez.ruben.pokedexcompose.ui.viewmodel.LoginViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokedexViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonDetailViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.PokemonHuntViewModel
import gutierrez.ruben.pokedexcompose.ui.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PokedexTheme {
                val loginViewModel: LoginViewModel = viewModel()
                val pokedexViewModel: PokedexViewModel = viewModel()
                val detailViewModel: PokemonDetailViewModel = viewModel()
                val huntViewModel: PokemonHuntViewModel = viewModel()
                val profileViewModel: ProfileViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokedexNavHost(
                        innerPadding = innerPadding,
                        loginViewModel = loginViewModel,
                        pokedexViewModel = pokedexViewModel,
                        detailViewModel = detailViewModel,
                        huntViewModel = huntViewModel,
                        profileViewModel = profileViewModel
                    )
                }
            }
        }
    }
}
