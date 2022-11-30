package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ): HomeMovieCategoryViewState {
        return HomeMovieCategoryViewState(movieCategories.map { movieCategory ->
            MovieCategoryLabelViewState(
                itemId = movieCategory.ordinal,
                isSelected = movieCategory == selectedMovieCategory,
                categoryText = MovieCategoryLabelTextViewState.LabelTextFromResource(
                    getCategory(movieCategory)
                )
            )
        }, movies.map { movie ->
            HomeMovieViewState(
                id = movie.id,
                movieCardViewState = MovieCardViewState(
                    movie.imageUrl.toString(),
                    movie.isFavorite
                )
            )
        })
    }

    private fun getCategory(movieCategory: MovieCategory): Int {
        return when (movieCategory) {
            MovieCategory.POPULAR_STREAMING -> R.string.popular_streaming
            MovieCategory.POPULAR_ON_TV -> R.string.popular_on_tv
            MovieCategory.POPULAR_FOR_RENT -> R.string.popular_for_rent
            MovieCategory.POPULAR_IN_THEATRES -> R.string.popular_in_theatres
            MovieCategory.NOW_PLAYING_MOVIES -> R.string.now_playing_movies
            MovieCategory.NOW_PLAYING_TV -> R.string.now_playing_TV
            MovieCategory.UPCOMING_TODAY -> R.string.upcoming_today
            MovieCategory.UPCOMING_THIS_WEEK -> R.string.upcoming_this_week
        }
    }
}
