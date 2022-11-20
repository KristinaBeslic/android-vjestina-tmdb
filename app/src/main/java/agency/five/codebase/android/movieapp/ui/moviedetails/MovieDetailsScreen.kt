package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.widget.Space
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()
val movieDetailsViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
) {
    val movieDetails by remember { mutableStateOf(movieDetailsViewState) }
    MovieDetailsScreen(movieDetails)
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        MovieDetailsHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.movie_details_header_size)),
            movieDetailsViewState = movieDetailsViewState
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
        MovieDetailsOverview(
            movieDetailsViewState = movieDetailsViewState,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
        TopBilledCast(
            movieDetailsViewState = movieDetailsViewState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MovieDetailsHeader(
    modifier: Modifier,
    movieDetailsViewState: MovieDetailsViewState
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.movie_details_user_score_padding_top),
                        start = dimensionResource(id = R.dimen.padding_large)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserScoreProgressBar(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.movie_details_user_score_size)),
                    userScore = movieDetailsViewState.voteAverage,
                    textColor = Color.White
                )
                Text(
                    text = stringResource(id = R.string.user_score),
                    fontSize = dimensionResource(id = R.dimen.movie_details_user_score_font_size).value.sp,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small)),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
            Text(
                text = movieDetailsViewState.title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = dimensionResource(id = R.dimen.movie_details_title_font_size).value.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(id = R.dimen.padding_large))
            )
            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
            FavouriteButton(
                color = colorResource(id = R.color.black_favourite_btn),
                onClick = {},
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_large))
            )
        }
    }
}

@Composable
fun MovieDetailsOverview(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.movie_details_overview),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.title_color_blue),
            fontSize = dimensionResource(id = R.dimen.movie_details_title_font_size).value.sp,
            modifier = Modifier.padding(
                start = dimensionResource(
                    id = R.dimen.movie_details_title_padding_start
                )
            )
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
        Text(
            text = movieDetailsViewState.overview,
            fontSize = dimensionResource(id = R.dimen.movie_details_overview_text_font_size).value.sp,
            color = Color.Black,
            modifier = Modifier.padding(
                start = dimensionResource(
                    id = R.dimen.movie_details_overview_text_padding_start
                ),
                end = dimensionResource(
                    id = R.dimen.movie_details_overview_text_padding_end
                )
            )
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_large)))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(id = R.dimen.movie_details_overview_text_padding_start)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            content = {
                items(
                    items = movieDetailsViewState.crew,
                    key = { crewman -> crewman.id }
                ) { crewman ->
                    CrewItem(crewman.crewmanViewState)
                }
            }
        )
    }
}

@Composable
fun TopBilledCast(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.movie_details_top_billed_cast),
            fontSize = dimensionResource(id = R.dimen.movie_details_title_font_size).value.sp,
            color = colorResource(id = R.color.title_color_blue),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.movie_details_title_padding_start))
        )
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.padding_small)))
        LazyRow(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = dimensionResource(id = R.dimen.padding_large)),
            horizontalArrangement = Arrangement
                .spacedBy(dimensionResource(id = R.dimen.row_space_by)),
            content = {
                items(
                    items = movieDetailsViewState.cast,
                    key = { actor -> actor.id }
                ) { actor ->
                    ActorCard(
                        actorCardViewState = actor.actorViewState,
                        modifier = Modifier.size(
                            width = dimensionResource(id = R.dimen.movie_details_actor_image_width),
                            height = dimensionResource(id = R.dimen.movie_details_actor_image_height)
                        )
                    )
                }
            },
        )
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        MovieDetailsScreen(movieDetailsViewState = movieDetailsViewState)
    }
}