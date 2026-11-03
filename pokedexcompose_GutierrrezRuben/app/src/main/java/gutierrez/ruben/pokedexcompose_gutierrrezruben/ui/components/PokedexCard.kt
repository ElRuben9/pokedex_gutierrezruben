package gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import gutierrez.ruben.pokedexcompose_gutierrrezruben.ui.theme.Dimens

private val CardShape = RoundedCornerShape(
    topStart = Dimens.CardCornerRadius,
    topEnd = Dimens.CardCornerRadius,
)

@Composable
fun PokedexCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, CardShape)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content,
    )
}
