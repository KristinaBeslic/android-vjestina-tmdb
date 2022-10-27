package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl: String
)

@Composable
fun MovieCard(
    movieCardViewState: MovieCardViewState,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
){
    Card(
        modifier = modifier
            .size(122.dp, 179.dp)
            .clickable { },
        shape = RoundedCornerShape(15.dp),
    ){
        Box(modifier = androidx.compose.ui.Modifier.fillMaxWidth()){
            AsyncImage(model = movieCardViewState.imageUrl,
                contentDescription = "Movie image")
            FavouriteButton(color = colorResource(R.color.BlackFavouriteBtn).copy(0.6f), modifier = modifier
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 81.dp,
                    bottom = 138.dp))
        }
    }
}

@Preview
@Composable
fun MovieCardPreview(){
    MovieCard(movieCardViewState = MovieCardViewState(MoviesMock.getMovieDetails().movie.imageUrl.toString()))
}