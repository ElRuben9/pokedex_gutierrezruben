package gutierrez.ruben.mipokedexcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.ui.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.mipokedexcompose.model.data.CompanionPreferences
import gutierrez.ruben.mipokedexcompose.model.data.Users
import gutierrez.ruben.mipokedexcompose.model.data.sqllite.DatabaseHelper
import gutierrez.ruben.mipokedexcompose.model.data.sqllite.PokemonDAO
import gutierrez.ruben.mipokedexcompose.model.data.sqllite.PokemonSQLRepository
import gutierrez.ruben.mipokedexcompose.model.domain.User
import gutierrez.ruben.mipokedexcompose.model.preferences.ARCHIVO
import gutierrez.ruben.mipokedexcompose.navigation.MyApp
import gutierrez.ruben.mipokedexcompose.ui.theme.Blue
import gutierrez.ruben.mipokedexcompose.ui.theme.Grey
import gutierrez.ruben.mipokedexcompose.ui.theme.LightBlue
import gutierrez.ruben.mipokedexcompose.ui.theme.MiPokedexComposeTheme
import gutierrez.ruben.mipokedexcompose.ui.theme.Pink
import gutierrez.ruben.mipokedexcompose.ui.theme.Red
import gutierrez.ruben.mipokedexcompose.ui.theme.White
import gutierrez.ruben.mipokedexcompose.view.screens.LoginScreen
import gutierrez.ruben.mipokedexcompose.viewModel.AddPokemonViewModel
import gutierrez.ruben.mipokedexcompose.viewModel.PokemonDetailViewModel
import gutierrez.ruben.mipokedexcompose.viewModel.UserViewModel
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val users = Users(this)
        val companionPreferences = CompanionPreferences(this)
        val usersViewModel = UserViewModel(users, companionPreferences)
        val pokemonDetailViewModel = PokemonDetailViewModel(companionPreferences)
        val databaseHelper = DatabaseHelper(this)
        val pokemonDAO = PokemonDAO(databaseHelper)
        val pokemonRepository = PokemonSQLRepository(pokemonDAO)
        val addPokemonViewModel = AddPokemonViewModel(pokemonRepository)
        enableEdgeToEdge()
        setContent {
            MiPokedexComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(innerPadding, usersViewModel, pokemonDetailViewModel, addPokemonViewModel)
                }
            }
        }
    }
}
