package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

@Composable
fun UserScoreProgressBar(
    userScore: Float,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    fontSize: TextUnit = 15.sp,
    radius: Dp = 42.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp
){
    val percentage: Float = userScore / 10

    Box(modifier = modifier.size(radius * 2f),
        contentAlignment = Alignment.Center){
        androidx.compose.foundation.Canvas(modifier = Modifier.size(radius * 2f)){
            drawArc(
                color = color.copy(0.2f),
                -90f,
                360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                -90f,
                360 * percentage.absoluteValue,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = userScore.toString(),
            modifier = modifier.padding(start = 10.dp,
                                        end = 7.dp,
                                        top = 13.dp,
                                        bottom = 13.dp),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold)
    }

}

@Preview
@Composable
fun UserScoreProgressBarPreview(){
    UserScoreProgressBar(userScore = 7.5.toFloat())
}