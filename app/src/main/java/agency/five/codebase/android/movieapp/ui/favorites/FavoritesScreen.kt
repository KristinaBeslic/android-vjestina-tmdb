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

private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()
val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (Int) -> Unit
) {
    val favoritesViewState by remember { mutableStateOf(favoritesViewState) }

    FavoritesScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
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
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.favorite_screen_header_spacer)))
    }
}

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState,
    onNavigateToMovieDetails: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
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
                                    dimensionResource(id = R.dimen.movie_card_width_favourite_screen),
                                    dimensionResource(id = R.dimen.movie_card_height_favourite_screen_landscape)
                                )
                            }
                            else -> {
                                Modifier.size(
                                    dimensionResource(id = R.dimen.movie_card_width_favourite_screen),
                                    dimensionResource(id = R.dimen.movie_card_height_favourite_screen)
                                )
                            }
                        },
                        onFavouriteClicked = {  },
                        onMovieCardClicked = { onNavigateToMovieDetails(card.id) })
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
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_large)),
            favoritesViewState = favoritesViewState,
            onNavigateToMovieDetails = {}
        )
    }
}