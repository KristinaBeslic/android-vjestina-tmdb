package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.size(
            width = dimensionResource(id = R.dimen.actor_card_width),
            height = dimensionResource(id = R.dimen.actor_card_height)
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape)),
        elevation = dimensionResource(id = R.dimen.actor_card_elevation)
    ) {
        AsyncImage(
            model = actorCardViewState.imageUrl,
            contentDescription = actorCardViewState.character,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.actor_image))
                .padding(bottom = dimensionResource(id = R.dimen.actor_image_bottom_padding))
        )
        Text(
            text = actorCardViewState.name,
            Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium),
                top = dimensionResource(id = R.dimen.actor_name_padding_top)
            ),
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.actor_name_font_size).value.sp
        )
        Text(
            text = actorCardViewState.character,
            modifier = modifier.padding(
                start = dimensionResource(id = R.dimen.padding_medium),
                top = dimensionResource(id = R.dimen.actor_character_padding_top)
            ),
            fontSize = dimensionResource(id = R.dimen.actor_character_font_size).value.sp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    ActorCard(
        actorCardViewState = ActorCardViewState(
            MoviesMock.getActor().imageUrl.toString(),
            MoviesMock.getActor().name,
            MoviesMock.getActor().character
        )
    )
}
