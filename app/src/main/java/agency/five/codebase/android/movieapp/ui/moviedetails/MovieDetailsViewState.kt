package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState

data class MovieDetailsViewState(
    val id: Int,
    val imageUrl: String?,
    val voteAverage: Float,
    val title: String,
    val overview: String,
    val isFavorite: Boolean,
    val crew: List<CrewmanViewState>,
    val cast: List<ActorViewState>,
) {
    companion object {
        val EMPTY: MovieDetailsViewState =
            MovieDetailsViewState(
                id = 0,
                imageUrl = "",
                voteAverage = 8.1.toFloat(),
                title = "",
                overview = "",
                isFavorite = false,
                crew = emptyList(),
                cast = emptyList()
            )
    }
}

data class CrewmanViewState(
    val id: Int,
    val crewmanViewState: CrewItemViewState
)

data class ActorViewState(
    val id: Int,
    val actorViewState: ActorCardViewState
)

