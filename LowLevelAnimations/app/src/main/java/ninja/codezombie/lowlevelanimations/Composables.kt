package ninja.codezombie.lowlevelanimations

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * Composable that shows an implementation of infinite transition
 * in Jetpack Compose in the form of two circles rotating around
 * the X and Y axes when requested
 */
@Composable
fun WaitingCircles(waiting: Boolean = false) {

    val infTransition = rememberInfiniteTransition()
    val rotX by infTransition.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotY by infTransition.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            tween(1000),
            repeatMode =  RepeatMode.Reverse
        )
    )

    Canvas(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .graphicsLayer { rotationZ = 45f }
            .switchRotationXAnimation(waiting, rotX)
//            .graphicsLayer {
//                rotationX = rotX
//            }
    ) {
        drawCircle(Color.Black, style = Stroke(width = 10f))
    }

    Canvas(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
            .graphicsLayer { rotationZ = 45f }
            .switchRotationYAnimation(waiting, rotY)
//            .graphicsLayer {
//                rotationY = rotY
//            }
    ) {
        drawCircle(Color.Black, style = Stroke(width = 10f))

    }



}


/**
 * Extension function returning modifier that performs rotation
 * around the X-axis when requested or an empty modifier otherwise
 */
fun Modifier.switchRotationXAnimation(switch: Boolean, rotX: Float): Modifier {
    return if (switch){
        this
            .graphicsLayer {
                rotationX = rotX
            }
    } else {
        this
    }
}

/**
 * Extension function returning modifier that performs rotation
 * around the Y-axis when requested or an empty modifier otherwise
 */
fun Modifier.switchRotationYAnimation(switch: Boolean, rotY: Float): Modifier {
    return if (switch){
        this
            .graphicsLayer {
                rotationY = rotY
            }
    }
    else this
}

/**
 * Composable that shows how to animate color in Jetpack Compose
 */
@Preview
@Composable
fun ColorAnimation() {
    var favorite by remember{mutableStateOf(false)}

    Box(Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center){
        IconButton(onClick = { favorite = !favorite }, Modifier.fillMaxSize()) {
            Icon(
                imageVector = if (favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                tint =  animateColorAsState(if (favorite) Color.Red else Color.Blue,
                    animationSpec = tween(durationMillis = 500)
                ).value,
                contentDescription = stringResource(R.string.like),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }
}


/**
 * Composable that shows usage of Dp animation in Jetpack Compose
 */
@Preview
@Composable
fun DpAnimation() {
    var liked by remember{mutableStateOf(false)}
    val boxElevation by animateDpAsState(if (liked) 0.dp else 4.dp)
    val roundness by animateDpAsState(if (liked) 16.dp else 0.dp)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
//        .shadow(
//            elevation = animateDpAsState(if (liked) 0.dp else 8.dp).value
//        )
    ){
        Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(topStart = roundness, bottomEnd = roundness))
                .background(
                    color =
                    if (liked) Color.Red else Color.Blue,
                    shape = RoundedCornerShape(topStart = roundness, bottomEnd = roundness)
                )
                .border(
                    1.dp,
                    Color.White,
                    shape = RoundedCornerShape(topStart = roundness, bottomEnd = roundness)
                )
                .shadow(elevation = boxElevation)
                .clickable { liked = !liked }){
            Image(painter = painterResource(id = R.drawable.eye),
            contentDescription = "Nature",
            contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )


        }
//        IconButton(onClick = { liked = !liked }, modifier = Modifier
//            .align(Alignment.Center)) {
//            Icon(imageVector = Icons.Outlined.ThumbUp,
//            contentDescription = stringResource(R.string.thumb_up),
//            modifier = Modifier
//                .fillMaxSize()
//                .shadow(elevation = animateDpAsState(targetValue = if (liked) 0.dp else 4.dp).value, shape =
//                CircleShape)
//                .padding(2.dp)
//            )
//        }

    }
}

@Preview
@Composable
fun FloatAnimation() {
    var toggleFloatAnimation by remember{mutableStateOf(false)}
    val progress by animateFloatAsState(if (toggleFloatAnimation) 300f else 0f,
        animationSpec = tween(1000)
    )


    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.Yellow)
        .clickable{toggleFloatAnimation = !toggleFloatAnimation}
        ){


        drawLine(color = Color.Blue,
        strokeWidth = 20f,
        start = Offset(size.width * 0.1f, size.height / 2),
        end = Offset(size.width * 0.1f + progress, size.height / 2),
        cap = StrokeCap.Round)
    }
}