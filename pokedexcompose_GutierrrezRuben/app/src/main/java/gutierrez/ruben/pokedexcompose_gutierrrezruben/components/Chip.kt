package components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Fire


@Composable
fun NumberChip(text: String, modifier: Modifier = Modifier, colors: Array<Color>) {
    Row(
        modifier = modifier
            .size(30.dp)
            .padding(5.dp)
            .background(color = colors[0], shape = CircleShape),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Black,
            color = colors[1]
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberChipPreview() {
    NumberChip(
        text = "25",
        colors = arrayOf(Fire, Color.DarkGray)
    )
}