package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavouriteButton(
    color: Color,
    modifier: Modifier = Modifier.size(dimensionResource(id = R.dimen.favourite_button_size)),
    isFavourite: Boolean = false,
    onClick: (Boolean) -> Unit
) {
    Image(
        painter = painterResource(id = if (isFavourite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite),
        contentDescription = null,
        modifier = modifier
            .clickable {
                onClick(isFavourite.not())
            }
            .background(color, CircleShape)
            .padding(
                start = dimensionResource(id = R.dimen.padding_small), end = dimensionResource(
                    id = R.dimen.padding_small
                ), top = dimensionResource(id = R.dimen.padding_medium), bottom = dimensionResource(
                    id = R.dimen.padding_medium
                )
            )
    )
}

@Preview
@Composable
fun FavouriteButtonPreview() {
    var isFavourite by remember { mutableStateOf(false) }

    FavouriteButton(colorResource(id = R.color.gray), isFavourite = isFavourite) {
        isFavourite = it
    }
}