package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()
val movies = MoviesMock.getMoviesList()
val popular = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ON_TV,
    MovieCategory.POPULAR_FOR_RENT,
    MovieCategory.POPULAR_IN_THEATRES
)
val nowPlaying = listOf(
    MovieCategory.NOW_PLAYING_MOVIES,
    MovieCategory.NOW_PLAYING_TV
)
val upcoming = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THIS_WEEK
)

val popularCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(
        popular,
        MovieCategory.POPULAR_STREAMING,
        movies
    )
val nowPlayingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(
        nowPlaying,
        MovieCategory.NOW_PLAYING_MOVIES,
        movies
    )
val upcomingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(
        upcoming,
        MovieCategory.UPCOMING_TODAY,
        movies
    )

@Composable
fun HomeRoute(
    onNavigateToMovieDetails: (Int) -> Unit
) {
    var popularCategoryViewState by remember { mutableStateOf(popularCategoryViewState) }
    var nowPlayingCategoryViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingCategoryViewState by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        popular = popularCategoryViewState,
        nowPlaying = nowPlayingCategoryViewState,
        upcoming = upcomingCategoryViewState,
        modifier = Modifier,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onCategoryClick = { categoryId ->
            when (categoryId) {
                MovieCategory.POPULAR_STREAMING.ordinal,
                MovieCategory.POPULAR_ON_TV.ordinal,
                MovieCategory.POPULAR_FOR_RENT.ordinal,
                MovieCategory.POPULAR_IN_THEATRES.ordinal -> {
                    popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                        popular,
                        MovieCategory.values()[categoryId],
                        movies
                    )
                }
                MovieCategory.NOW_PLAYING_MOVIES.ordinal,
                MovieCategory.NOW_PLAYING_TV.ordinal -> {
                    nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                        nowPlaying,
                        MovieCategory.values()[categoryId],
                        movies
                    )
                }
                MovieCategory.UPCOMING_TODAY.ordinal,
                MovieCategory.UPCOMING_THIS_WEEK.ordinal -> {
                    upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
                        upcoming,
                        MovieCategory.values()[categoryId],
                        movies
                    )
                }
            }
        }
    )
}

@Composable
fun HomeScreen(
    popular: HomeMovieCategoryViewState,
    nowPlaying: HomeMovieCategoryViewState,
    upcoming: HomeMovieCategoryViewState,
    modifier: Modifier,
    onNavigateToMovieDetails: (Int) -> Unit,
    onCategoryClick: (Int) -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
        MoviesSection(
            homeMovieCategoryViewState = popular,
            title = stringResource(id = R.string.whats_popular),
            modifier = Modifier.fillMaxWidth(),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onCategoryClick = onCategoryClick
        )
        MoviesSection(
            homeMovieCategoryViewState = nowPlaying,
            title = stringResource(id = R.string.now_playing),
            modifier = Modifier.fillMaxWidth(),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onCategoryClick = onCategoryClick
        )
        MoviesSection(
            homeMovieCategoryViewState = upcoming,
            title = stringResource(id = R.string.upcoming),
            modifier = Modifier.fillMaxWidth(),
            onNavigateToMovieDetails = onNavigateToMovieDetails,
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
                        onFavouriteClicked = { /*TODO*/ },
                        onMovieCardClicked = { onNavigateToMovieDetails(movie.id) })
                }
            }
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen(
            popular = popularCategoryViewState,
            nowPlaying = nowPlayingCategoryViewState,
            upcoming = upcomingCategoryViewState,
            modifier = Modifier.fillMaxSize(),
            onNavigateToMovieDetails = {},
            onCategoryClick = {}
        )
    }
}