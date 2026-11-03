package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import gutierrez.ruben.pokedexcompose_gutierrrezruben.R
import gutierrez.ruben.pokedexcompose_gutierrrezruben.data.pokemonList
import gutierrez.ruben.pokedexcompose_gutierrrezruben.domain.Pokemon
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.FavoritesRow
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.MenuPokedex
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.PokedexGrid
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components.SectionLabel
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Blue
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dimens
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Green
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.LightBlue
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.LightGreen
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.MiPokedexTheme

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    pokemonList: List<Pokemon>,
    favoriteList: List<Pokemon>,
    grid: Boolean,
    onGridChange: (Boolean) -> Unit,
    onPokemonClick: (Pokemon) -> Unit,
    onHuntClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column {
            Row(
                modifier = Modifier.padding(top = Dimens.MarginSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SectionLabel(
                    text = stringResource(R.string.label_favorites),
                    modifier = Modifier.weight(1f),
                )
                LogoutButton(
                    onClick = onLogoutClick,
                    modifier = Modifier.padding(end = Dimens.MarginSmall),
                )
            }
            if (favoriteList.isEmpty()) {
                Text(
                    text = stringResource(R.string.favorites_empty),
                    modifier = Modifier.padding(Dimens.MarginMedium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            } else {
                FavoritesRow(favoriteList, onPokemonClick)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                SectionLabel(
                    text = stringResource(R.string.label_all_pokemons),
                    modifier = Modifier.weight(1f),
                )
                ViewSwitch(
                    grid = grid,
                    onGridChange = onGridChange,
                    modifier = Modifier.padding(end = Dimens.MarginMedium),
                )
            }
            if (grid) {
                PokedexGrid(pokemonList, onPokemonClick, bottomPadding = Dimens.FabSpace)
            } else {
                MenuPokedex(pokemonList, onPokemonClick, bottomPadding = Dimens.FabSpace)
            }
        }
        HuntButton(
            onClick = onHuntClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimens.MarginMedium),
        )
    }
}

@Composable
private fun ViewSwitch(
    grid: Boolean,
    onGridChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Switch(
        checked = grid,
        onCheckedChange = onGridChange,
        modifier = modifier,
        thumbContent = {
            Icon(
                painter = painterResource(if (grid) R.drawable.ic_grid else R.drawable.ic_list),
                contentDescription = stringResource(
                    if (grid) R.string.cd_view_grid else R.string.cd_view_list,
                ),
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Green,
            checkedTrackColor = LightGreen,
            uncheckedThumbColor = Blue,
            uncheckedTrackColor = LightBlue,
            uncheckedBorderColor = Color.Transparent,
        ),
    )
}

@Composable
private fun LogoutButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
        ),
    ) {
        Text(stringResource(R.string.logout))
    }
}

@Composable
private fun HuntButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onSurface,
        icon = {
            Image(
                painter = painterResource(R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier.size(Dimens.FabIconSize),
            )
        },
        text = { Text(stringResource(R.string.go_hunt)) },
    )
}

@Preview(showBackground = true)
@Composable
private fun MenuPokedexScreenPreview() {
    MiPokedexTheme {
        MenuPokedexScreen(
            innerPadding = PaddingValues(),
            pokemonList = pokemonList,
            favoriteList = pokemonList.filter { it.favorite },
            grid = false,
            onGridChange = {},
            onPokemonClick = {},
            onHuntClick = {},
            onLogoutClick = {},
        )
    }
}
