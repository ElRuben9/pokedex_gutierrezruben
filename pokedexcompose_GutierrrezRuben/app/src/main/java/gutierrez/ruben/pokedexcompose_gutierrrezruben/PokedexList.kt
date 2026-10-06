package gutierrez.ruben.pokedexcompose_gutierrrezruben

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import gutierrez.ruben.pokedexcompose_gutierrrezruben.screens.MenuPokedexScreen

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold { innerPadding ->
                MenuPokedexScreen(innerPadding = innerPadding)
            }
        }
    }
}