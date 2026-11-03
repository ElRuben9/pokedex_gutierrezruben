package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import gutierrez.ruben.pokedexcompose_gutierrrezruben.R
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.bulbasaur
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dimens
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.MiPokedexTheme
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.OffWhite
import gutierrez.ruben.pokedexcompose_gutierrrezruben.utilities.getColorByType

private const val INFO_WIDTH = 0.7f
private const val STATS_WIDTH = 0.85f

private val DescriptionFontSize = 10.sp
private val DescriptionLineHeight = 13.sp

@Composable
fun PokemonRow(pokemon: Pokemon, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Dimens.RowPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = stringResource(R.string.cd_row_image, pokemon.name),
            modifier = Modifier
                .width(Dimens.RowImageWidth)
                .padding(Dimens.RowPadding),
        )
        Column(
            modifier = Modifier.fillMaxWidth(INFO_WIDTH),
            verticalArrangement = Arrangement.spacedBy(Dimens.RowTextSpacing),
        ) {
            Text(text = pokemon.name, style = MaterialTheme.typography.labelLarge)
            Text(
                text = pokemon.description,
                fontSize = DescriptionFontSize,
                lineHeight = DescriptionLineHeight,
            )
            Row(
                modifier = Modifier.fillMaxWidth(STATS_WIDTH),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.row_height, pokemon.height),
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = stringResource(R.string.row_weight, pokemon.weight),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
        NumberChip(pokemon.number.toString(), getColorByType(pokemon.type))
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, onClick: () -> Unit) {
    val colors = getColorByType(pokemon.type)
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = Dimens.FavoritePaddingVertical),
        verticalArrangement = Arrangement.spacedBy(Dimens.FavoriteSpacing),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            Box(
                modifier = Modifier.border(
                    BorderStroke(
                        width = Dimens.FavoriteBorderWidth,
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                colors.first,
                                OffWhite,
                                colors.first,
                                OffWhite,
                                colors.first,
                            ),
                        ),
                    ),
                ),
            ) {
                Image(
                    painter = painterResource(pokemon.image),
                    contentDescription = stringResource(R.string.cd_row_image, pokemon.name),
                    modifier = Modifier
                        .width(Dimens.FavoriteImageWidth)
                        .padding(Dimens.FavoriteImagePadding),
                )
            }
            NumberChip(
                text = pokemon.number.toString(),
                colors = colors,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(Dimens.FavoriteChipOffsetX, Dimens.FavoriteChipOffsetY),
            )
        }
        Text(text = pokemon.name, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(Dimens.CellPadding),
    ) {
        Column(
            modifier = Modifier.padding(Dimens.CellPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = stringResource(R.string.cd_row_image, pokemon.name),
                modifier = Modifier
                    .widthIn(max = Dimens.CellImageSize)
                    .aspectRatio(1f)
                    .padding(Dimens.CellImagePadding),
            )
            Text(text = pokemon.name, style = MaterialTheme.typography.labelLarge)
        }
        NumberChip(
            text = pokemon.number.toString(),
            colors = getColorByType(pokemon.type),
            modifier = Modifier.align(Alignment.TopEnd),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonRowPreview() {
    MiPokedexTheme {
        PokemonRow(bulbasaur, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritePokemonPreview() {
    MiPokedexTheme {
        FavoritePokemon(bulbasaur, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonCellPreview() {
    MiPokedexTheme {
        PokemonCell(bulbasaur, onClick = {})
    }
}
