package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String,
    var isFavorite: MutableState<Boolean>
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: Modifier = Modifier.size(
        width = dimensionResource(id = R.dimen.movie_card_width),
        height = dimensionResource(id = R.dimen.movie_card_height)
    ),
    onClick: (Boolean) -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner_shape))
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = "Movie image",
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            FavouriteButton(
                color = colorResource(R.color.black_favourite_btn).copy(ButtonConstant.BUTTON_TRANSPARENCY),
                modifier = modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_small),
                        start = dimensionResource(id = R.dimen.padding_small)
                    )
                    .wrapContentSize(align = Alignment.TopStart),
                isFavourite = movieCardViewState.isFavorite.value
            ) {
                movieCardViewState.isFavorite.value = it
            }
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
            isFavorite = remember {
                mutableStateOf(MoviesMock.getMovieDetails().movie.isFavorite)
            }), onClick = { }
    )
}