package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState
)

sealed class MovieCategoryLabelTextViewState {
    class LabelTextFromString(val text: String): MovieCategoryLabelTextViewState()
    class LabelTextFromResource(@StringRes val textRes: Int): MovieCategoryLabelTextViewState()
}

@Composable
fun MovieCategoryLabel(
    movieCategoryLabelViewState: MovieCategoryLabelViewState
){
    Column{
        when(val text = movieCategoryLabelViewState.categoryText){
            is MovieCategoryLabelTextViewState.LabelTextFromString ->
                Text(text = text.text,
                    fontSize = 16.sp,
                    fontWeight = if(movieCategoryLabelViewState.isSelected) FontWeight.Bold else FontWeight.Light,
                    color = if(movieCategoryLabelViewState.isSelected) Color.Black else Color.Gray)
            is MovieCategoryLabelTextViewState.LabelTextFromResource ->
                Text(text = text.textRes.toString(),
                    fontSize = 16.sp,
                    fontWeight = if(movieCategoryLabelViewState.isSelected) FontWeight.Bold else FontWeight.Light,
                    color = if(movieCategoryLabelViewState.isSelected) Color.Black else Color.Gray)
        }

        if(movieCategoryLabelViewState.isSelected) {
            Spacer(modifier = Modifier.size(3.dp))
            Divider(thickness = 4.dp, color = Color.Black)
        }
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview(){
    val textFromString = MovieCategoryLabelTextViewState.LabelTextFromString("Movies")
    MovieCategoryLabel(movieCategoryLabelViewState = MovieCategoryLabelViewState(3, true, textFromString))
}
