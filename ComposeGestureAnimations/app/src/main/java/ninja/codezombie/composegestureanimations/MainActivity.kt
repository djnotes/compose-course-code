package ninja.codezombie.composegestureanimations

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ninja.codezombie.composegestureanimations.ui.theme.ComposeGestureAnimationsTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGestureAnimationsTheme {
                GestureAnimationDemo()
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun GestureAnimationDemo() {
    Column(modifier = Modifier
        .fillMaxHeight()
    ){
        val offset = remember{ Animatable(Offset(0f, 0f), Offset.VectorConverter) }

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color(0xFFE91E63))
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        val pos = awaitPointerEventScope { awaitFirstDown().position }
                        launch {
                            offset.animateTo(
                                pos,
                                spring(dampingRatio = Spring.DampingRatioHighBouncy)
                            )
                        }
                    }
                }
            }
        ) {

            Image(painter = painterResource(id = R.drawable.bee), contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .offset { IntOffset(offset.value.x.roundToInt(), offset.value.y.roundToInt()) }
            )

            Text(stringResource(R.string.click_in_box_to_move_bee),
            textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            )




        }

        Divider(modifier = Modifier
            .height(6.dp)
            .background(Color.Black)
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(Color(0xFF03A9F4))

        ){
            var dismissed by remember{mutableStateOf(false)}

            this@Column.AnimatedVisibility (!dismissed,
            exit = shrinkOut(
                shrinkTowards = Alignment.TopCenter,
                targetSize = {size -> IntSize(size.width, 0) },
                animationSpec = tween(500)
            ),
                ) {
                Text(stringResource(R.string.swipe_to_dismiss), style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(5.dp))
                        .clip(RoundedCornerShape(5.dp))
                        .border(2.dp, Color.Blue, RoundedCornerShape(5.dp))
                        .swipeToDismiss { dismissed = true }
                )
            }

            Text(stringResource(R.string.drag_to_the_side_to_dismiss),
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center))

            if(dismissed) {
                Toast.makeText(LocalContext.current, "Dismissed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

fun Modifier.swipeToDismiss(onDismissed : () -> Unit): Modifier = composed {
    val offsetX = remember{Animatable(0f)}

    pointerInput(Unit){
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            val velocityTracker = VelocityTracker()
            while (true){
                val pointerId  = awaitPointerEventScope { awaitFirstDown().id }
                launch{offsetX.stop()}
                awaitPointerEventScope {
                    horizontalDrag(pointerId){ change ->
                        launch { offsetX.snapTo(offsetX.value + change.positionChange().x) }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                    }
                }

                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)

                offsetX.updateBounds(
                    lowerBound = - size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )

                launch {
                    if(targetOffsetX.absoluteValue > 0.75 * size.width){
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    } else {
                        offsetX.animateTo(0f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
                    }
                }

            }
        }
    }
        .offset { IntOffset(offsetX.value.roundToInt(), 0) }
}