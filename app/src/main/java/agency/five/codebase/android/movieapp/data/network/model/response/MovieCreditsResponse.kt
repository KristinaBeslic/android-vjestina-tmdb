package agency.five.codebase.android.movieapp.data.network.model.response

import agency.five.codebase.android.movieapp.data.network.model.ApiCast
import agency.five.codebase.android.movieapp.data.network.model.ApiCrew
import kotlinx.serialization.SerialName

class MovieCreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<ApiCast>,
    @SerialName("crew")
    val crew: List<ApiCrew>
)