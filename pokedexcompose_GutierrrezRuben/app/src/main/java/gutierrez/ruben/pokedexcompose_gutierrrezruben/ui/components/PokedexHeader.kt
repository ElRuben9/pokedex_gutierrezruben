package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import gutierrez.ruben.pokedexcompose_gutierrrezruben.R
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.bulbasaur
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dimens
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.MiPokedexTheme

@Composable
fun PokedexHeader(pokemon: Pokemon, onFavoriteClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(start = Dimens.MarginLarge, top = Dimens.MarginLarge),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = pokemon.name,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(R.string.pokemon_number, pokemon.number),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
        Image(
            painter = painterResource(
                if (pokemon.favorite) R.drawable.ic_star else R.drawable.ic_star_border,
            ),
            contentDescription = stringResource(R.string.cd_favorite),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .toggleable(
                    value = pokemon.favorite,
                    role = Role.Checkbox,
                    onValueChange = { onFavoriteClick() },
                )
                .padding(Dimens.MarginSmall)
                .size(Dimens.FavoriteSize),
        )
    }
}

@Preview
@Composable
private fun PokedexHeaderPreview() {
    MiPokedexTheme {
        PokedexHeader(
            pokemon = bulbasaur,
            onFavoriteClick = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        )
    }
}
