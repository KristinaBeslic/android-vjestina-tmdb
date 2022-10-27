package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
) {
    Card(modifier = Modifier.size(125.dp, 209.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        AsyncImage(
            model = actorCardViewState.imageUrl,
            contentDescription = actorCardViewState.character,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                 .width(125.dp)
                .height(170.47.dp)
                .padding(bottom = 65.dp)
        )
        Text(text = actorCardViewState.name,
            Modifier.padding(start = 10.dp,
                top=147.dp,
                end=28.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp)
        Text(text = actorCardViewState.character,
            modifier = Modifier.padding(start = 10.dp,
                end = 7.dp,
                top = 185.dp,
                bottom = 2.dp),
            fontSize = 11.9.sp,
            color = Color.Gray)
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    ActorCard(actorCardViewState = ActorCardViewState(MoviesMock.getActor().imageUrl.toString(), MoviesMock.getActor().name, MoviesMock.getActor().character))
}
