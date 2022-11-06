package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.R

data class CrewItemViewState(
    val name: String,
    val job: String
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = crewItemViewState.name,
            fontSize = dimensionResource(id = R.dimen.crew_item_font_size).value.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = crewItemViewState.job,
            fontSize = dimensionResource(id = R.dimen.crew_item_font_size).value.sp
        )
    }
}

@Preview
@Composable
private fun CrewItemPreview() {
    CrewItem(
        crewItemViewState = CrewItemViewState(
            MoviesMock.getCrewman().name,
            MoviesMock.getCrewman().job
        )
    )
}