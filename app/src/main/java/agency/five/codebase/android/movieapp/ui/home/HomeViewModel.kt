package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    homeScreenMapper: HomeScreenMapper,
) : ViewModel() {
    private val popular = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES
    )
    private val nowPlaying = listOf(
        MovieCategory.NOW_PLAYING_MOVIES,
        MovieCategory.NOW_PLAYING_TV
    )
    private val upcoming = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK
    )

    private val popularSelected = MutableStateFlow(MovieCategory.POPULAR_STREAMING)
    private val nowPlayingSelected = MutableStateFlow(MovieCategory.NOW_PLAYING_MOVIES)
    private val upcomingSelected = MutableStateFlow(MovieCategory.UPCOMING_TODAY)

    val popularCategoryHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        combine(
            movieRepository.popularMovies(MovieCategory.POPULAR_STREAMING),
            popularSelected
        ) { movies, selectedCategory ->
            homeScreenMapper.toHomeMovieCategoryViewState(
                movieCategories = popular,
                selectedMovieCategory = selectedCategory,
                movies = movies
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 1000L,
                    replayExpirationMillis = 0L
                ),
                initialValue = HomeMovieCategoryViewState.EMPTY
            )

    val nowPlayingCategoryHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        combine(
            movieRepository.popularMovies(MovieCategory.NOW_PLAYING_TV),
            nowPlayingSelected
        ) { movies, selectedCategory ->
            homeScreenMapper.toHomeMovieCategoryViewState(
                movieCategories = nowPlaying,
                selectedMovieCategory = selectedCategory,
                movies = movies
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 1000L,
                    replayExpirationMillis = 0L
                ),
                initialValue = HomeMovieCategoryViewState.EMPTY
            )

    val upcomingCategoryHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        combine(
            movieRepository.upcomingMovies(MovieCategory.UPCOMING_TODAY),
            upcomingSelected
        ){ movies, selectedCategory ->
            homeScreenMapper.toHomeMovieCategoryViewState(
                movieCategories = upcoming,
                selectedMovieCategory = selectedCategory,
                movies = movies
            )
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 1000L,
                    replayExpirationMillis = 0L
                ),
                initialValue = HomeMovieCategoryViewState.EMPTY
            )

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }

    fun switchCategory(categoryId: Int) {
        when (categoryId) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_IN_THEATRES.ordinal -> {
                popularSelected.update { MovieCategory.values()[categoryId] }
            }
            MovieCategory.NOW_PLAYING_MOVIES.ordinal,
            MovieCategory.NOW_PLAYING_TV.ordinal -> {
                nowPlayingSelected.update { MovieCategory.values()[categoryId] }
            }
            MovieCategory.UPCOMING_TODAY.ordinal,
            MovieCategory.UPCOMING_THIS_WEEK.ordinal -> {
                upcomingSelected.update { MovieCategory.values()[categoryId] }
            }
        }

    }
}