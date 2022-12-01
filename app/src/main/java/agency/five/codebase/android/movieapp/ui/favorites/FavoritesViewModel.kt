package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    favoritesScreenMapper: FavoritesMapper,
) : ViewModel() {
    private val _favoritesViewState = MutableStateFlow(FavoritesViewState())
    val favoritesViewState: StateFlow<FavoritesViewState> = _favoritesViewState

    init {
        viewModelScope.launch {
            movieRepository.favoriteMovies()
                .collect {
                    _favoritesViewState.value = favoritesScreenMapper.toFavoritesViewState(it)
                }
        }
    }
}