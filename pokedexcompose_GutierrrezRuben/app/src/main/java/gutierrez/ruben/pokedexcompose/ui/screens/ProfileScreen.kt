package gutierrez.ruben.pokedexcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gutierrez.ruben.pokedexcompose.ui.theme.Grey
import gutierrez.ruben.pokedexcompose.ui.theme.Pink40
import gutierrez.ruben.pokedexcompose.ui.theme.Purple40
import gutierrez.ruben.pokedexcompose.ui.theme.Typography
import gutierrez.ruben.pokedexcompose.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    viewModel: ProfileViewModel
) {
    val companionCard by viewModel.companion.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = viewModel.name ?: "Ruben Gutierrez",
            style = Typography.displayLarge,
            color = Pink40,
            textAlign = TextAlign.Center
        )
        Text(
            text = viewModel.email ?: "ruben.gutierrez@correo.com",
            style = Typography.labelMedium,
            color = Grey
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Mi compañero",
            style = Typography.titleLarge,
            color = Pink40
        )

        Spacer(Modifier.height(16.dp))

        if (companionCard.pokemon.exists) {
            Image(
                painter = painterResource(companionCard.pokemon.image),
                contentDescription = "${companionCard.pokemon.name} image",
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = companionCard.pokemon.name,
                style = Typography.labelLarge,
                fontStyle = FontStyle.Italic
            )
            Spacer(Modifier.height(10.dp))
            CompanionDaysLabel(days = companionCard.daysTogether)
        } else {
            Text(
                text = "Todavía no has elegido un compañero.",
                style = Typography.labelMedium,
                color = Grey,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CompanionDaysLabel(days: Long?) {
    val message = when {
        days == null -> "Aún no sabemos cuántos días llevan juntos."
        days <= 0L -> "Es tu compañero desde hoy"
        days == 1L -> "Tiene 1 día de ser tu compañero"
        else -> "Tiene $days días de ser tu compañero"
    }

    Text(
        text = message,
        style = Typography.labelMedium,
        color = Purple40,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 4.dp)
    )
}
