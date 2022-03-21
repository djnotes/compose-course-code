package ninja.codezombie.animatedcontentdraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import ninja.codezombie.animatedcontentdraft.ui.theme.AnimatedContentDraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedContentDraftTheme {
                MyAnimatedContent()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun MyAnimatedContent() {
    var count by remember{ mutableStateOf(0)}
    var expand by remember{mutableStateOf(false)}

    val boxSize = 100.dp


    Column {
        Canvas(
            Modifier
                .size(300.dp)
                .padding(16.dp)){
            drawArc(Color.Blue, startAngle = 0f, sweepAngle = count.toFloat(), useCenter = true)
        }

//        Slider(value = count.toFloat(), onValueChange = {count = it.toInt()}, modifier = Modifier.padding(16.dp).fillMaxWidth(), valueRange = 0f..360f, steps = 1)
        Row(
            Modifier
                .padding(16.dp)
                .shadow(2.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.arrow_up), "Add",
                Modifier
                    .size(boxSize)
                    .clickable {
                        count++
                    })
            Divider(Modifier.width(16.dp))
            AnimatedContent(targetState = count,
                transitionSpec = {
                    if (targetState > initialState) {
//                        ContentTransform(
//                            targetContentEnter = slideInVertically({ 2*it }),
//                            initialContentExit = slideOutVertically({ -2 * it })
//                        )
//                        slideInVertically({height -> height}, animationSpec = tween(200)) with slideOutVertically({height -> -height}, animationSpec = tween(1000))
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Up) with slideOutOfContainer(AnimatedContentScope.SlideDirection.Up)
                    } else {
//                        ContentTransform(
//                            targetContentEnter = slideInVertically(),
//                            initialContentExit = slideOutVertically()
//                        )
//                        slideInVertically({height -> -height}, animationSpec = tween(200)) with slideOutVertically({height -> height}, tween(1000))
                        slideIntoContainer(AnimatedContentScope.SlideDirection.Down) with slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Down)

                    }
                }) { count ->
                Box(Modifier, contentAlignment = Alignment.Center) {
                    Text(
                        "$count",
                        Modifier
                            .size(boxSize)
                            .padding(16.dp)
                            .align(Alignment.Center),
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Divider(Modifier.width(16.dp))
            Icon(
                painterResource(id = R.drawable.arrow_down), "Delete",
                Modifier
                    .size(boxSize)
                    .clickable {
                        if (count > 0) count--
                    })


        }


        AnimatedContent(targetState = expand,
            transitionSpec = {
//                    slideIntoContainer(towards = AnimatedContentScope.SlideDirection.End) with slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Start)
                    expandIn(expandFrom = Alignment.TopStart) + slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Up) with shrinkOut(Alignment.TopCenter) using
                        SizeTransform(clip = true, sizeAnimationSpec = {initalSize, targetSize ->
//                            KeyframesSpec(KeyframesSpec.KeyframesSpecConfig<IntSize>().apply{
//                                durationMillis = 300
////                                IntSize(300,300) at 200
//
//                            })
                            spring(stiffness = Spring.StiffnessLow)
                        })


            }

        ){targetState ->
            if (targetState){
                Box(
                    Modifier
                        .padding(16.dp)
                        .background(Color.Red)
                        .clickable { expand = !expand }){
                    Image(
                        painterResource(id = R.drawable.professor), "Phone",
                        Modifier
                            .size(boxSize)
                            .align(Alignment.BottomEnd)
                    ,
                        contentScale = ContentScale.Fit

                    )
                    Text(stringResource(id = R.string.lorem_ipsum), textDecoration = TextDecoration.Underline,
                        overflow = TextOverflow.Ellipsis, color = Color.White, modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .align(Alignment.TopStart))

                }
            }
            else {
                Image(
                    painterResource(id = R.drawable.professor), "Professor",
                    Modifier
                        .size(boxSize)
                        .padding(16.dp)
                        .graphicsLayer { rotationY = 180f }
                        .clickable { expand = !expand }
                )

            }
        }

    }
}