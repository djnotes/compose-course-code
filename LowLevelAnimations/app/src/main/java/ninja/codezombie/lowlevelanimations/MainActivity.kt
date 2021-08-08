package ninja.codezombie.lowlevelanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ninja.codezombie.lowlevelanimations.ui.theme.LowLevelAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LowLevelAnimationsTheme {
                AnimationDemo()
            }
        }
    }
}

enum class MyAnims{
    Infinite,
    AnimateColor,
    AnimateDp,
    AnimateFloat,
    AnimateInt,
    AnimateIntSize,
    AnimateIntOffset,
    AnimateSize,
    AnimateRect,
    AnimateValue
}


val ROW_HEIGHT = 100.dp
val BOX_COLOR = Color.LightGray

@Preview
@Composable
fun AnimationDemo() {

    val infiniteTransition = rememberInfiniteTransition()
    val (infSwitch, onInfSwitchChange) = remember { mutableStateOf(false) }
    val scrollState = rememberScrollableState(consumeScrollDelta = {delta -> delta})

    //TODO: CREATE AN INFINITE TRANSITION
    val infiniteColor by infiniteTransition.animateColor(
        initialValue = Color.LightGray,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, 100, LinearEasing)
        )
    )


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),

        elevation = 2.dp
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, Color.Black)
                .shadow(2.dp)
                .scrollable(
                    state = scrollState, orientation = Orientation.Vertical
                )
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(title = {
                Text(
                    stringResource(R.string.app_name), Modifier
                        .padding(8.dp)
                )

            },
                navigationIcon = {},
                actions = {})


            for (animation in MyAnims.values()) {
                val name = when (animation) {
                    MyAnims.Infinite -> {
                        stringResource(R.string.infinite_transition)
                    }
                    MyAnims.AnimateColor -> {
                        stringResource(R.string.animating_color)
                    }
                    MyAnims.AnimateDp -> {
                        stringResource(R.string.animate_dp)
                    }
                    MyAnims.AnimateFloat -> {
                        stringResource(R.string.animate_float)
                    }
                    MyAnims.AnimateValue -> {
                        stringResource(R.string.animate_value)
                    }
                    else -> {
                        animation.name
                    }

                }



                Row(
                    Modifier
                        .padding(4.dp)
                        .height(ROW_HEIGHT)
//                        .weight(2f)
                        .border(2.dp, Color.Black, RoundedCornerShape(5.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (animation == MyAnims.Infinite) {
                        Text(name,
                            Modifier
                                .padding(8.dp)
                                .weight(1f))
                        Switch(
                            checked = infSwitch,
                            onCheckedChange = onInfSwitchChange,
                            modifier = Modifier
                                .weight(1f)
                        )
                    } else {
                        Button(
                            onClick = { /*TODO*/ }, Modifier
                                .weight(2f)
                                .padding(8.dp)
                        ) {
                            Text(name, Modifier.padding(8.dp))
                        }
                    }



                    Box(
                        Modifier
                            .padding(4.dp)
                            .weight(2f)
                            .fillMaxHeight()
                            .background(BOX_COLOR)
                            .clip(RoundedCornerShape(5.dp))
                    ) {
                        when (animation) {
                            MyAnims.Infinite -> {
                                WaitingCircles(waiting = infSwitch)
                            }

                            MyAnims.AnimateColor -> {
                                ColorAnimation()
                            }

                            MyAnims.AnimateFloat -> {
                                FloatAnimation()
                            }

                            MyAnims.AnimateValue -> {

                            }

                            MyAnims.AnimateDp -> {
                                DpAnimation()
                            }

                            else -> {
                                Text("Not implemented", modifier = Modifier
                                    .padding(16.dp),
                                textAlign = TextAlign.Center)
                            }

                        }

                    }
                }


            }


        }
    }
}
