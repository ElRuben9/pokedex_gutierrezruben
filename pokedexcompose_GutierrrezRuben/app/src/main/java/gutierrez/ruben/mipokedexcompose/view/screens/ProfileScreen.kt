package gutierrez.ruben.mipokedexcompose.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import gutierrez.ruben.mipokedexcompose.R
import gutierrez.ruben.mipokedexcompose.ui.theme.Pink40
import gutierrez.ruben.mipokedexcompose.ui.theme.Typography
import gutierrez.ruben.mipokedexcompose.viewModel.UserViewModel

@Composable
fun ProfileScreen(innerPadding: PaddingValues, userViewModel: UserViewModel = viewModel()
) {
    val nombre = if (userViewModel.currentUser != null) userViewModel.currentUser!! .name else "Nombre usuario"

    val mail = if (userViewModel.currentUser != null) userViewModel.currentUser!! .email else "usuario@mail.com"

    //val image = if (userViewModel.currentUser != null) userViewModel.companion.value.image else R.drawable.wtp

    //val pokemonName = if (userViewModel.currentUser != null) userViewModel.companion.name else "No selected"

    val pokemon by userViewModel.companion.collectAsStateWithLifecycle()


    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text (
            nombre, style = Typography.displayLarge, color = Pink40
        )
        Text(mail)
        Spacer(Modifier.size(50.dp))
        Text("Mi compañero,", style = Typography.titleLarge, color = Pink40)
        Image(painter = painterResource(pokemon.image), contentDescription = "pokemonImage")
        Text(pokemon.name, style = Typography.labelMedium, fontStyle = FontStyle.Italic)
        if (pokemon.companionSince.isNotEmpty()) {
            val days = ChronoUnit.DAYS.between(
                LocalDate.parse(pokemon.companionSince),
                LocalDate.now()
            )
            Text("Tiene $days ${if (days == 1L) "día" else "días"} de ser tu compañero")
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen(PaddingValues(0.dp),viewModel())
}