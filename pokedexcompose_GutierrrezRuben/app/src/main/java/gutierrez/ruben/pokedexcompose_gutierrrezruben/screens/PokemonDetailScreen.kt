package gutierrez.ruben.pokedexcompose_gutierrrezruben.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.R
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities.getColorByType

@Composable
fun PokemonDetailScreen(pokemonNumber: Int) {
    val pokemon = pokemonList.find { it.number == pokemonNumber } ?: pokemonList.first()
    val typeColors = getColorByType(pokemon.type)

    Box(
        Modifier
            .fillMaxSize()
            .background(typeColors.first)
    ) {
        Image(
            painter = painterResource(R.drawable.bg_pokeball),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.TopEnd)
                .offset(40.dp, (-10).dp)
        )

        Column(Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = pokemon.name,
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = String.format("#%03d", pokemon.number),
                        color = Color(0xFF2C3E50),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Image(
                    painter = painterResource(R.drawable.ic_star_outline),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(Modifier.height(130.dp))

            Box(
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(typeColors.first)
                            .padding(horizontal = 20.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = pokemon.type,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = typeColors.second
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row {
                                Text("Altura  ", color = Color(0xFFB0262C), fontWeight = FontWeight.Bold)
                                Text("${pokemon.height} m")
                            }
                            Row {
                                Text("Peso  ", color = Color(0xFFB0262C), fontWeight = FontWeight.Bold)
                                Text("${pokemon.weight} kg")
                            }
                        }
                        Column {
                            Text("Habilidad", color = Color(0xFFB0262C), fontWeight = FontWeight.Bold)
                            Text(pokemon.ability)
                        }
                    }

                    Spacer(Modifier.height(26.dp))

                    Text(
                        text = pokemon.description,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF555555),
                        fontSize = 15.sp
                    )
                }
            }
        }

        Image(
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(190.dp)
                .align(Alignment.TopCenter)
                .offset(y = 100.dp)
        )
    }
}