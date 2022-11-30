package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()
val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (Int) -> Unit
) {
    val favoritesViewState by remember { mutableStateOf(favoritesViewState) }

    FavoritesScreen(
        favoritesViewState = favoritesViewState,
        onNavigateToMovieDetails = onNavigateToMovieDetails
    )
}

@Composable
fun FavoritesHeader(
    modifier: Modifier
) {
    Column() {
        Text(
            text = stringResource(id = R.string.favorites),
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium),
                    top = dimensionResource(id = R.dimen.favorite_screen_header_padding_top)
                ),
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.favorites_font_size).value.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.favorite_screen_header_spacer)))
    }
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoritesViewState: FavoritesViewState,
    onNavigateToMovieDetails: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.favorite_screen_grid_padding)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            content = {
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                ) {
                    FavoritesHeader(modifier = Modifier.fillMaxWidth())
                }
                items(
                    items = favoritesViewState.favoriteMovies,
                    key = { movie -> movie.id }
                ) { card ->
                    MovieCard(
                        movieCardViewState = card.movieCardViewState,
                        modifier =
                        when (LocalConfiguration.current.orientation) {
                            Configuration.ORIENTATION_LANDSCAPE -> {
                                Modifier.size(
                                    dimensionResource(id = R.dimen.favourite_screen_movie_card_width),
                                    dimensionResource(id = R.dimen.favourite_screen_landscape_movie_card_height)
                                )
                            }
                            else -> {
                                Modifier.size(
                                    dimensionResource(id = R.dimen.favourite_screen_movie_card_width),
                                    dimensionResource(id = R.dimen.favourite_screen_movie_card_height)
                                )
                            }
                        },
                        onFavouriteClicked = { },
                        onMovieCardClicked = { onNavigateToMovieDetails(card.id) }
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState = favoritesViewState,
            onNavigateToMovieDetails = {}
        )
    }
}