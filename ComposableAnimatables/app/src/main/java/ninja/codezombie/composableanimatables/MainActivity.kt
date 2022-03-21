package ninja.codezombie.composableanimatables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ninja.codezombie.composableanimatables.ui.theme.ComposableAnimatablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableAnimatablesTheme {
                AnimatableAnimationDemo()
            }
        }
    }
}

@Preview
@Composable
fun AnimatableAnimationDemo() {
    val bgColor = remember{ Animatable(Color.Red) }
    val (checked, onCheckedChange) = remember{mutableStateOf(false)}

    val boxHeight = remember{Animatable(200f)}

    LaunchedEffect(key1 = checked){
        bgColor.animateTo(if (checked) Color.Yellow else Color.Blue)
    }


    Column(modifier = Modifier
        .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dp(boxHeight.value))
                .padding(16.dp)
                .background(bgColor.value)
                .border(2.dp, Color.Black)
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        coroutineScope {
                            awaitPointerEventScope {
                                awaitFirstDown()
                                launch{
                                    boxHeight.animateTo(400f,
                                    animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy,
                                    stiffness = Spring.StiffnessMedium)
                                    )

                                }
                                launch{
                                    bgColor.animateTo(Color.Cyan)
                                }


                            }

                            awaitPointerEventScope {
                                waitForUpOrCancellation()
                                launch{
                                    boxHeight.animateTo(300f)
                                    bgColor.animateTo(Color.LightGray)
                                }
                            }

                        }
                    })
                }
        ) {
        }

        Checkbox(checked, onCheckedChange)
    }

}