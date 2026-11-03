package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import gutierrez.ruben.pokedexcompose_gutierrrezruben.R
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.bulbasaur
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.PokedexCard
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.PokedexHeader
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.PokedexNavigation
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.PokemonStats
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.TypeChips
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dimens
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.MiPokedexTheme
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.viewmodel.PokemonDetailState
private const val CARD_TOP = 0.29f
private const val IMAGE_TOP = 0.10f
private const val IMAGE_BOTTOM = 0.335f
private val DescriptionAlignment = BiasAlignment.Vertical(-0.5f)

@Composable
fun PokemonDetailScreen(
    detail: PokemonDetailState?,
    onFavoriteClick: () -> Unit,
    onNeighborClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
    ) {
        if (detail == null) {
            Text(
                text = stringResource(R.string.pokemon_not_found),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
            )
        } else {
            PokemonDetail(
                pokemon = detail.pokemon,
                previous = detail.previous,
                next = detail.next,
                onFavoriteClick = onFavoriteClick,
                onNeighborClick = onNeighborClick,
            )
        }
    }
}

@Composable
private fun BoxScope.PokemonDetail(
    pokemon: Pokemon,
    previous: Pokemon,
    next: Pokemon,
    onFavoriteClick: () -> Unit,
    onNeighborClick: (Pokemon) -> Unit,
) {
    Image(
        painter = painterResource(R.drawable.ic_pokeball),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.TopEnd)
            .offset(x = Dimens.PokeballOffsetX, y = Dimens.PokeballOffsetY)
            .size(Dimens.PokeballSize),
    )
    PokedexCard(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxHeight(1f - CARD_TOP),
    ) {
        TypeChips(pokemon.type, Modifier.padding(top = Dimens.MarginTypeTop))
        PokemonStats(pokemon, Modifier.padding(top = Dimens.MarginMedium))
        Text(
            text = pokemon.description,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.MarginDescription)
                .wrapContentHeight(DescriptionAlignment),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        PokedexNavigation(previous = previous, next = next, onPokemonClick = onNeighborClick)
    }
    PokedexHeader(pokemon, onFavoriteClick)
    PokemonPortrait(pokemon)
}

@Composable
private fun PokemonPortrait(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(IMAGE_TOP))
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = stringResource(R.string.cd_pokemon_image, pokemon.name),
            modifier = Modifier
                .weight(IMAGE_BOTTOM - IMAGE_TOP)
                .aspectRatio(1f),
        )
        Spacer(Modifier.weight(1f - IMAGE_BOTTOM))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PokemonDetailScreenPreview() {
    MiPokedexTheme {
        PokemonDetailScreen(
            detail = PokemonDetailState(
                pokemon = bulbasaur,
                previous = pokemonList.last(),
                next = pokemonList[1],
            ),
            onFavoriteClick = {},
            onNeighborClick = {},
        )
    }
}
