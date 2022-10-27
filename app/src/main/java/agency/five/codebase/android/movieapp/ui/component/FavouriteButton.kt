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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteButton(
    color: Color,
    modifier: Modifier = Modifier
) {
    var isFavourite by remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = if (isFavourite) R.drawable.ic_favourite_filled else R.drawable.ic_favourite),
        contentDescription = null,
        modifier = modifier
            .clickable {
                isFavourite = isFavourite.not()
            }
            .size(32.dp)
            .background(color, CircleShape)
            .padding(start = 8.dp, end = 8.dp, top = 10.dp, bottom = 10.dp)
    )
}

@Preview
@Composable
fun FavouriteButtonPreview(){
    FavouriteButton(colorResource(id = R.color.Gray))
}