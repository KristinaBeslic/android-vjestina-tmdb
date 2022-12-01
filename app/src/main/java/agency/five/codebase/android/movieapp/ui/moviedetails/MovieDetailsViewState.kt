package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewItemViewState

data class MovieDetailsViewState(
    val id: Int = 0,
    val imageUrl: String? = "",
    val voteAverage: Float = 8.1.toFloat(),
    val title: String = "",
    val overview: String = "",
    val isFavorite: Boolean = true,
    val crew: List<CrewmanViewState> = listOf(),
    val cast: List<ActorViewState> = listOf(),
)

data class CrewmanViewState(
    val id: Int,
    val crewmanViewState: CrewItemViewState
)

data class ActorViewState(
    val id: Int,
    val actorViewState: ActorCardViewState
)

