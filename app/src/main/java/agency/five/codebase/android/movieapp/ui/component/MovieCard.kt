package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String,
    var isFavorite: Boolean
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier,
    onFavouriteClicked: () -> Unit,
    onMovieCardClicked: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onMovieCardClicked() },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            FavouriteButton(color = colorResource(R.color.black_favourite_btn).copy(ButtonConstant.BUTTON_TRANSPARENCY),
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_small),
                        start = dimensionResource(id = R.dimen.padding_small)
                    )
                    .wrapContentSize(align = Alignment.TopStart),
                isFavourite = movieCardViewState.isFavorite,
                onClick = { onFavouriteClicked() })
        }
    }
}

object ButtonConstant {
    const val BUTTON_TRANSPARENCY = 0.6f
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieCard(
        movieCardViewState = MovieCardViewState(
            MoviesMock.getMovieDetails().movie.imageUrl.toString(),
            isFavorite = MoviesMock.getMovieDetails().movie.isFavorite
        ),
        onFavouriteClicked = {},
        onMovieCardClicked = {}
    )
}