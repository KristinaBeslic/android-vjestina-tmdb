package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Canvas
import kotlin.math.absoluteValue

@Composable
fun UserScoreProgressBar(
    userScore: Float,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = dimensionResource(id = R.dimen.user_score_font_size).value.sp,
    color: Color = Color.Green,
    strokeWidth: Dp = dimensionResource(id = R.dimen.padding_small)
) {
    val percentage: Float = userScore / 10

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = color.copy(ProgressBarConstants.CIRCLE_TRANSPARENCY),
                ProgressBarConstants.START_ANGLE,
                ProgressBarConstants.SWEEP_ANGLE.toFloat(),
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                ProgressBarConstants.START_ANGLE,
                 ProgressBarConstants.SWEEP_ANGLE * percentage.absoluteValue,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = userScore.toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

object ProgressBarConstants {
    const val START_ANGLE = -90f
    const val SWEEP_ANGLE = 360
    const val CIRCLE_TRANSPARENCY = 0.2f
}

@Preview
@Composable
fun UserScoreProgressBarPreview() {
    UserScoreProgressBar(userScore = 7.5.toFloat())
}