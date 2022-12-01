package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    homeScreenMapper: HomeScreenMapper,
) : ViewModel() {
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

    private val _popularCategoryHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val popularCategoryHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _popularCategoryHomeViewState.asStateFlow()

    private val _nowPlayingCategoryHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val nowPlayingCategoryHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _nowPlayingCategoryHomeViewState.asStateFlow()

    private val _upcomingCategoryHomeViewState = MutableStateFlow(HomeMovieCategoryViewState())
    val upcomingCategoryHomeViewState: StateFlow<HomeMovieCategoryViewState> =
        _upcomingCategoryHomeViewState.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.popularMovies(MovieCategory.POPULAR_STREAMING)
                .collect {
                    _popularCategoryHomeViewState.value =
                        homeScreenMapper.toHomeMovieCategoryViewState(
                            movieCategories = popular,
                            selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
                            movies = it
                        )
                }
        }
        viewModelScope.launch {
            movieRepository.popularMovies(MovieCategory.NOW_PLAYING_MOVIES)
                .collect {
                    _nowPlayingCategoryHomeViewState.value =
                        homeScreenMapper.toHomeMovieCategoryViewState(
                            movieCategories = nowPlaying,
                            selectedMovieCategory = MovieCategory.NOW_PLAYING_MOVIES,
                            movies = it
                        )
                }
        }
        viewModelScope.launch {
            movieRepository.popularMovies(MovieCategory.UPCOMING_TODAY)
                .collect {
                    _upcomingCategoryHomeViewState.value =
                        homeScreenMapper.toHomeMovieCategoryViewState(
                            movieCategories = upcoming,
                            selectedMovieCategory = MovieCategory.UPCOMING_TODAY,
                            movies = it
                        )
                }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}