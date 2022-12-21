package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.MovieDetails
import androidx.annotation.StringRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("movie")
    val movie: ApiMovie,
    @SerialName("id")
    val id: Int,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("language")
    val language: String,
    @SerialName("runtime")
    val runtime: Int
) {
    fun toMovieDetails(
        isFavorite: Boolean,
        crew: List<Crewman>,
        cast: List<Actor>
    ) = MovieDetails(
        movie = movie.toMovie(isFavorite = isFavorite),
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        language = language,
        runtime = runtime,
        crew = crew,
        cast = cast
    )
}