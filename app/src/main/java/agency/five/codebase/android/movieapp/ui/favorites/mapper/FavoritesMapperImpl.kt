package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper{
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        return FavoritesViewState(favoriteMovies.map { movie ->
            FavoritesMovieViewState(
                id = movie.id,
                movieCardViewState = MovieCardViewState(movie.imageUrl.toString(), movie.isFavorite)
            )
        })
    }
}