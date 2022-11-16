package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.R

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState {
    class LabelTextFromString(val text: String) : MovieCategoryLabelTextViewState()
    class LabelTextFromResource(@StringRes val textRes: Int) : MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onClick() }
            .width(intrinsicSize = IntrinsicSize.Max)
    ) {
        when (val text = movieCategoryLabelViewState.categoryText) {
            is MovieCategoryLabelTextViewState.LabelTextFromString ->
                Text(
                    text = text.text,
                    fontSize = dimensionResource(id = R.dimen.movie_category_label_font_size).value.sp,
                    style = if (movieCategoryLabelViewState.isSelected) {
                        TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    } else {
                        TextStyle(
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        )
                    }
                )
            is MovieCategoryLabelTextViewState.LabelTextFromResource ->
                Text(
                    text = text.textRes.toString(),
                    fontSize = dimensionResource(id = R.dimen.movie_category_label_font_size).value.sp,
                    style = if (movieCategoryLabelViewState.isSelected) {
                        TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    } else {
                        TextStyle(
                            fontWeight = FontWeight.Light,
                            color = Color.Gray
                        )
                    }
                )
        }
        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.movie_label_spacer_size)))

        if (movieCategoryLabelViewState.isSelected) {
            Divider(
                thickness = dimensionResource(id = R.dimen.movie_label_thickness),
                color = Color.Black
            )
        } else {
            Divider(
                thickness = dimensionResource(id = R.dimen.movie_label_thickness),
                color = Color.Transparent
            )
        }
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview() {
    val textFromString = MovieCategoryLabelTextViewState.LabelTextFromString("Movies")
    MovieCategoryLabel(
        movieCategoryLabelViewState = MovieCategoryLabelViewState(
            3,
            true,
            textFromString
        ), onClick = {}
    )
}
