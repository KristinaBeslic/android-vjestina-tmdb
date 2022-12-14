package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieId: Int,
    private val movieRepository: MovieRepository,
    movieDetailsMapper: MovieDetailsMapper,
) : ViewModel() {
    val movieDetailsViewState: StateFlow<MovieDetailsViewState> =
        movieRepository.movieDetails(movieId)
            .map { movieDetails ->
                movieDetailsMapper.toMovieDetailsViewState(movieDetails = movieDetails)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 1000L,
                    replayExpirationMillis = 0L
                ),
                initialValue = MovieDetailsViewState.EMPTY
            )

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(movieId)
        }
    }
}