package gutierrez.ruben.pokedexcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose.R
import gutierrez.ruben.pokedexcompose.data.Pokemon
import gutierrez.ruben.pokedexcompose.ui.theme.Green
import gutierrez.ruben.pokedexcompose.ui.theme.Grey
import gutierrez.ruben.pokedexcompose.ui.theme.Purple40
import gutierrez.ruben.pokedexcompose.ui.theme.Red
import gutierrez.ruben.pokedexcompose.ui.theme.TypePalette
import gutierrez.ruben.pokedexcompose.ui.theme.White
import gutierrez.ruben.pokedexcompose.ui.theme.paletteFor

@Composable
fun PokemonDetailCard(
    pokemon: Pokemon,
    isCompanion: Boolean,
    notice: String?,
    onChooseCompanion: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = White),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 60.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TypeBadge(type = pokemon.type, palette = paletteFor(pokemon.type))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Altura",
                            modifier = Modifier.padding(end = 10.dp),
                            color = Red,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "${pokemon.height}", fontSize = 25.sp)
                    }
                    Row(
                        modifier = Modifier.padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Peso",
                            modifier = Modifier.padding(end = 10.dp),
                            color = Red,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "${pokemon.weight}", fontSize = 25.sp)
                    }
                }

                Column {
                    Text(
                        text = "Habilidad",
                        color = Red,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = pokemon.ability, fontSize = 20.sp)
                }
            }

            Text(
                text = pokemon.description,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)
            )

            CompanionSection(
                isCompanion = isCompanion,
                notice = notice,
                onChooseCompanion = onChooseCompanion
            )

            Spacer(modifier = Modifier.size(16.dp))

            NeighbourRow()
        }
    }
}

@Composable
private fun CompanionSection(
    isCompanion: Boolean,
    notice: String?,
    onChooseCompanion: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (isCompanion) {
            Row(
                modifier = Modifier.padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.estrella),
                    contentDescription = "Estrella de compañero",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(Modifier.size(6.dp))
                Text(
                    text = "Es tu compañero",
                    color = Purple40,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        } else {
            Button(
                onClick = onChooseCompanion,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Seleccionar compañero")
            }
        }

        if (notice != null) {
            Text(
                text = notice,
                modifier = Modifier.padding(top = 10.dp),
                color = Green,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun TypeBadge(type: String, palette: TypePalette) {
    Card(
        colors = CardDefaults.cardColors(containerColor = palette.background),
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = type,
            modifier = Modifier.padding(horizontal = 45.dp, vertical = 5.dp),
            textAlign = TextAlign.Center,
            color = palette.content
        )
    }
}

@Composable
private fun NeighbourRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Neighbour(
            imageRes = R.drawable.brionne,
            label = "Brionne N.º 0729",
            arrowRes = R.drawable.flecha_atras,
            arrowFirst = true
        )
        Neighbour(
            imageRes = R.drawable.pikipek,
            label = "Pikipek N.º 0731",
            arrowRes = R.drawable.flecha_delante,
            arrowFirst = false
        )
    }
}

@Composable
private fun Neighbour(
    imageRes: Int,
    label: String,
    arrowRes: Int,
    arrowFirst: Boolean
) {
    Column(
        horizontalAlignment = if (arrowFirst) Alignment.Start else Alignment.End
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = label,
            modifier = Modifier.size(140.dp),
            contentScale = ContentScale.Fit
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (arrowFirst) {
                Arrow(arrowRes)
                Spacer(Modifier.size(4.dp))
                NeighbourLabel(label)
            } else {
                NeighbourLabel(label)
                Spacer(Modifier.size(4.dp))
                Arrow(arrowRes)
            }
        }
    }
}

@Composable
private fun NeighbourLabel(label: String) {
    Text(text = label, fontSize = 14.sp, color = Grey)
}

@Composable
private fun Arrow(res: Int) {
    Box(
        modifier = Modifier
            .size(18.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Grey),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = res),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(12.dp)
        )
    }
}
