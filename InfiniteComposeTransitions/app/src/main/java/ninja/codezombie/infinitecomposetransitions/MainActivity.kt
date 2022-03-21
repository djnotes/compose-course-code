package ninja.codezombie.infinitecomposetransitions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ninja.codezombie.infinitecomposetransitions.ui.theme.InfiniteComposeTransitionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfiniteComposeTransitionsTheme {
                Column(Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally){
                    MyInfiniteTransitionDemo1()
                    MyInfiniteTransitionDemo2()
                }
            }
        }
    }
}

@Preview
@Composable
fun MyInfiniteTransitionDemo1() {
    val infiniteTransition = rememberInfiniteTransition()
    val lineStart by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .fillMaxSize(0.5f)
            .padding(8.dp)
    ){
        Canvas(Modifier
            .fillMaxSize()){
            drawLine(Color.Blue, start = Offset(lineStart, size.height / 2), end = Offset(lineStart + 200f, size.height / 2) ,
                strokeWidth = 10f)
        }
    }
}

@Preview
@Composable
fun MyInfiniteTransitionDemo2() {
    val myInfiniteTransition = rememberInfiniteTransition()
    val pitch by myInfiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )

    val yaw by myInfiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )


    Box(modifier = Modifier
        .fillMaxSize(0.5f)){
        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .align(Alignment.Center)
            .graphicsLayer {
                rotationX = pitch
            }
            ){
            rotate(45f) { drawCircle(Color.Black, style = Stroke(4f)) }
        }

        Canvas(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .align(Alignment.Center)
            .graphicsLayer {
                rotationY = yaw
            }
        ){
            rotate(45f) { drawCircle(Color.Black, style = Stroke(4f)) }
        }
    }
}

