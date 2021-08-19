package ninja.codezombie.valueanimation

import android.animation.TypeConverter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ninja.codezombie.valueanimation.ui.theme.ValueAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValueAnimationTheme {
                ValueAnimationDemo()
            }
        }
    }
}


class MySize(val width: Float, val height: Float)

@Preview
@Composable
fun ValueAnimationDemo() {
    var clicked by remember{ mutableStateOf(false)}
    val roundness by animateDpAsState(if (clicked) 25.dp else 0.dp)

    val mySize : MySize by animateValueAsState(targetValue =
        if (clicked) MySize(200f, 200f) else MySize(250f, 250f),
        typeConverter =  TwoWayConverter<MySize, AnimationVector2D>(
        convertToVector = {size -> AnimationVector2D(size.width, size.height)},
        convertFromVector = {vector -> MySize(vector.v1, vector.v2)}
        ))



    Box(modifier = Modifier
        .background(
            animateColorAsState(if (clicked) Color.Blue else Color.LightGray).value
        )
        .fillMaxSize()) {
        Box(modifier =
        Modifier
            .padding(16.dp)
            .background(Color.Red, RoundedCornerShape(roundness))
            .clip(RoundedCornerShape(roundness))
            .clickable { clicked = !clicked }
            .align(Alignment.TopCenter)
            .size(Dp(mySize.width), Dp(mySize.height))

        )
    }







}