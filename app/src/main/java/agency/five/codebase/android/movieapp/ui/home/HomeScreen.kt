package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.component.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel,
    onNavigateToMovieDetails: (Int) -> Unit
){
    val popularCategoryViewState: HomeMovieCategoryViewState by viewModel.popularCategoryHomeViewState.collectAsState()
    val nowPlayingCategoryViewState: HomeMovieCategoryViewState by viewModel.nowPlayingCategoryHomeViewState.collectAsState()
    val upcomingCategoryViewState: HomeMovieCategoryViewState by viewModel.upcomingCategoryHomeViewState.collectAsState()

    HomeScreen(
        popular = popularCategoryViewState,
        nowPlaying = nowPlayingCategoryViewState,
        upcoming = upcomingCategoryViewState,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onCategoryClick = { viewModel.switchCategory(it) },
        onFavoriteButtonClick = { viewModel.toggleFavorite(it) }
    )
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    modifier: Modifier = Modifier,
    onNavigateToMovieDetails: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
        MoviesSection(
            homeMovieCategoryViewState = popular,
            title = stringResource(id = R.string.whats_popular),
            modifier = Modifier.fillMaxWidth(),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClick = onFavoriteButtonClick,
            onCategoryClick = onCategoryClick
        )
        MoviesSection(
            homeMovieCategoryViewState = nowPlaying,
            title = stringResource(id = R.string.now_playing),
            modifier = Modifier.fillMaxWidth(),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClick = onFavoriteButtonClick,
            onCategoryClick = onCategoryClick
        )
        MoviesSection(
            homeMovieCategoryViewState = upcoming,
            title = stringResource(id = R.string.upcoming),
            modifier = Modifier.fillMaxWidth(),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onFavoriteButtonClick = onFavoriteButtonClick,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
fun Title(
    title: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            color = colorResource(id = R.color.title_color_blue),
            fontSize = dimensionResource(id = R.dimen.home_screen_title_font_size).value.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_large))
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
    }
}

@Composable
fun Section(
    modifier: Modifier,
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    onCategoryClick: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dimensionResource(id = R.dimen.padding_large)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        content = {
            items(
                items = homeMovieCategoryViewState.movieCategories,
                key = { category -> category.itemId }
            ) { category ->
                MovieCategoryLabel(
                    movieCategoryLabelViewState = category,
                    onClick = { onCategoryClick(category.itemId) }
                )
            }
        })
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
}

@Composable
fun MoviesSection(
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    title: String,
    modifier: Modifier,
    onNavigateToMovieDetails: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit
) {
    Column(modifier = modifier) {
        Title(title = title, modifier = Modifier.fillMaxWidth())
        Section(
            modifier = Modifier.fillMaxWidth(),
            homeMovieCategoryViewState = homeMovieCategoryViewState,
            onCategoryClick = onCategoryClick
        )
        LazyRow(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = dimensionResource(id = R.dimen.padding_large)),
            horizontalArrangement = Arrangement
                .spacedBy(dimensionResource(id = R.dimen.row_space_by)),
            content = {
                items(
                    items = homeMovieCategoryViewState.movies,
                    key = { movie -> movie.id }
                ) { movie ->
                    MovieCard(
                        movieCardViewState = movie.movieCardViewState,
                        modifier = Modifier.size(
                            dimensionResource(id = R.dimen.movie_card_width),
                            dimensionResource(id = R.dimen.movie_card_height)
                        ),
                        onFavouriteClicked = { onFavoriteButtonClick(movie.id) },
                        onMovieCardClicked = { onNavigateToMovieDetails(movie.id) })
                }
            }
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
    }
}