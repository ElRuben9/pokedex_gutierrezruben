package gutierrez.ruben.pokedexcompose_gutierrrezruben

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PantallaPokedex() }
    }
}

@Composable
fun PantallaPokedex() {
    Box(Modifier.fillMaxSize().background(Color(0xFFF4D03F))) {
        Image(painterResource(R.drawable.bg_pokeball), null, Modifier.size(240.dp).align(Alignment.TopEnd).offset(40.dp, (-10).dp))

        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth().padding(24.dp), Arrangement.SpaceBetween) {
                Column {
                    Text("Pikachu", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text("#025", color = Color(0xFF2C3E50), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Image(painterResource(R.drawable.ic_star_outline), null, Modifier.size(32.dp))
            }

            Spacer(Modifier.height(130.dp))

            Box(Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)).background(Color.White).padding(20.dp)) {
                TarjetaDatos()
            }
        }

        Image(painterResource(R.drawable.img_pikachu), null, Modifier.size(190.dp).align(Alignment.TopCenter).offset(y = 100.dp))
    }
}

@Composable
fun TarjetaDatos() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.clip(RoundedCornerShape(16.dp)).background(Color(0xFFF9E75D)).padding(20.dp, 4.dp)) {
            Text("Electrico", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                ItemDato("Altura", "0,4m")
                ItemDato("Peso", "6,0kg")
            }
            Column {
                Text("Habilidad", color = Color(0xFFB0262C), fontWeight = FontWeight.Bold)
                Text("Elec. Estatica")
            }
        }

        Spacer(Modifier.height(26.dp))

        Text("Cuando se enfada, este Pokemon descarga la energía que almacena en el interior de las bolsas de las mejillas.", textAlign = TextAlign.Center, color = Color(0xFF555555), fontSize = 15.sp)

        Spacer(Modifier.weight(1f))

        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.Bottom) {
            Column(horizontalAlignment = Alignment.Start) {
                Image(painterResource(R.drawable.img_arbok), null, Modifier.size(75.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.ic_arrow_left), null, Modifier.size(16.dp))
                    Text(" Arbok N.º 0024", fontSize = 12.sp, color = Color(0xFF555555))
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Image(painterResource(R.drawable.img_raichu), null, Modifier.size(75.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Raichu N.º 0026 ", fontSize = 12.sp, color = Color(0xFF555555))
                    Image(painterResource(R.drawable.ic_arrow_right), null, Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun ItemDato(campo: String, valor: String) {
    Row {
        Text("$campo  ", color = Color(0xFFB0262C), fontWeight = FontWeight.Bold)
        Text(valor)
    }
}