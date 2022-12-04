package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (Int) -> Unit,
    viewModel: FavoritesViewModel
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()

    FavoritesScreen(
        favoritesViewState = favoritesViewState,
        onFavoriteButtonClick = viewModel::toggleFavorite,
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
    onFavoriteButtonClick: (Int) -> Unit,
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
                        onFavouriteClicked = { onFavoriteButtonClick(card.id) },
                        onMovieCardClicked = { onNavigateToMovieDetails(card.id) }
                    )
                }
            }
        )
    }
}