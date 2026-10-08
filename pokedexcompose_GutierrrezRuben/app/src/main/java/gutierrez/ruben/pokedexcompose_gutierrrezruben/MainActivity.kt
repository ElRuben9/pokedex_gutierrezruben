package gutierrez.ruben.pokedexcompose_gutierrrezruben

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import gutierrez.ruben.pokedexcompose_gutierrrezruben.navigation.PokedexNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexNavHost()
        }
    }
}