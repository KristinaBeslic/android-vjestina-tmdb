package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState>,
    val movies: List<HomeMovieViewState>
) {
    companion object {
        val EMPTY: HomeMovieCategoryViewState = HomeMovieCategoryViewState(emptyList(), emptyList())
    }
}

data class HomeMovieViewState(
    val id: Int,
    val movieCardViewState: MovieCardViewState
)