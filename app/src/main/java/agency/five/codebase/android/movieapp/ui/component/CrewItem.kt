package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState
){
    Column {
        Text(text = crewItemViewState.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold)
        Text(text = crewItemViewState.job,
            fontSize = 14.sp)
    }
}

@Preview
@Composable
private fun CrewItemPreview() {
    CrewItem(crewItemViewState = CrewItemViewState(MoviesMock.getCrewman().name, MoviesMock.getCrewman().job))
}