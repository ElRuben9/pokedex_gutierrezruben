package gutierrez.ruben.mipokedexcompose.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import gutierrez.ruben.mipokedexcompose.R
import gutierrez.ruben.mipokedexcompose.model.data.absol
import gutierrez.ruben.mipokedexcompose.model.domain.Pokemon
import gutierrez.ruben.mipokedexcompose.ui.theme.Blue
import gutierrez.ruben.mipokedexcompose.ui.theme.Grey
import gutierrez.ruben.mipokedexcompose.ui.theme.Pink
import gutierrez.ruben.mipokedexcompose.ui.theme.Red
import gutierrez.ruben.mipokedexcompose.ui.theme.White
import gutierrez.ruben.mipokedexcompose.utilities.getColorByType
import gutierrez.ruben.mipokedexcompose.viewModel.PokemonDetailViewModel

@Composable
fun CuadroBlanco(pokemon: Pokemon, pokemonDetailViewModel: PokemonDetailViewModel = viewModel()) {
    val companion = pokemonDetailViewModel.companion.collectAsState().value

    Card(
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        modifier = Modifier
            .padding(top = 200.dp, bottom = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 12.dp, top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Tipo(pokemon, getColorByType(pokemon.type))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                ) {
                    Column() {
                        Row(
                            modifier = Modifier.padding(start = 10.dp, bottom = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Height",
                                modifier = Modifier.padding(end = 10.dp),
                                color = Red,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = pokemon.height.toString(),
                                modifier = Modifier.padding(end = 80.dp),
                                fontSize = 25.sp
                            )
                        }
                        Row(
                            modifier = Modifier.padding(start = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Weight",
                                modifier = Modifier.padding(end = 10.dp),
                                color = Red,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = pokemon.weight.toString(),
                                fontSize = 25.sp
                            )
                        }
                    }
                    Column() {
                        Text(
                            text = "Ability",
                            color = Red,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = pokemon.ability,
                            fontSize = 20.sp
                        )
                    }
                }

            }
            Text(
                text = pokemon.description,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            )
            if (companion.number == pokemon.number) {
                Text(
                    text = "⭐Es tu compañero",
                    modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                Button(
                    onClick = { pokemonDetailViewModel.saveCompanion(pokemon) },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text("Seleccionar compañero")
                }
            }
            if (pokemonDetailViewModel.selectedCompanionNumber == pokemon.number) {
                Text(
                    text = "Compañero seleccionado",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    PokemonAnterior()
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    PokemonSiguiente()
                }
            }
        }
    }
}

@Composable
fun Tipo(pokemon: Pokemon, colors: Pair<Color, Color>) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colors.first
        ),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(text = pokemon.type,
            modifier = Modifier
                .padding(45.dp, 5.dp),
            textAlign = TextAlign.Center,
            color = colors.second
        )

    }
}

@Composable
fun PokemonAnterior() {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.brionne),
            contentDescription = "brionne",
            modifier = Modifier
                .size(140.dp)

        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlechaAnterior()
            Text(
                text = "Brionne N° 0729",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 4.dp),
            )
        }
    }
}

@Composable
fun PokemonSiguiente() {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Image(
            painter = painterResource(id = R.drawable.pikipek),
            contentDescription = "pikipek",
            modifier = Modifier
                .size(140.dp)

        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pikipek N° 0731",
                modifier = Modifier
                    .padding(start = 10.dp, end = 4.dp),
            )
            FlechaSiguiente()
        }
    }
}

@Composable
fun FlechaAnterior() {
    Image(
        painter = painterResource(id = R.drawable.flecha_atras),
        contentDescription = "flecha para atras",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(18.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Grey)
    )
}

@Composable
fun FlechaSiguiente() {
    Image(
        painter = painterResource(id = R.drawable.flecha_delante),
        contentDescription = "flecha para adelante",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(18.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Grey)
    )
}

@Preview(showBackground = true)
@Composable
fun CuadroBlancoPreview() {
    CuadroBlanco(absol)
}