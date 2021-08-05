package ninja.codezombie.swapcontentmotion


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ninja.codezombie.swapcontentmotion.ui.theme.SwapContentMotionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapContentMotionTheme {
                AnimationDemo()
            }
        }
    }
}

enum class Picture { Man, Woman, Daughter, Son, All }

/**
 * Composable to show Crossfade and AnimatedContent animations
 */
@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimationDemo() {
    /**
     * Count to keep track of the horizontal sliding counter
     */
    var count by remember { mutableStateOf(0) }

    /**
     * Flag to expand or shrink the text content
     */
    var expand by remember { mutableStateOf(false) }

    /**
     * Enum variable to select a picture to crossfade to
     */
    var pick by remember { mutableStateOf(Picture.Man) }


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(2.dp)
    ) {
        Column (Modifier
            .fillMaxHeight(0.4f)
            .padding(8.dp)
            ){
            Text(stringResource(id = R.string.animated_content_sample_1), Modifier.padding(8.dp),
            style = MaterialTheme.typography.h6)
            Divider()

            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp)
                    .background(Color.Yellow)
                ,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IconButton(onClick = { if(count > 0) count-- },
                modifier = Modifier
                    .weight(1f)) {
                    Icon(
                        painterResource(id = R.drawable.left_arrow), null, Modifier
                            .size(100.dp)
                    )
                }

                //Add AnimatedContent here around Box
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .width(100.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "$count",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                IconButton(onClick = { count++ },
                modifier = Modifier
                    .weight(1f)) {
                    Icon(
                        painterResource(id = R.drawable.right_arrow), null, Modifier
                            .size(100.dp)
                    )
                }

            }
        }


        Column(
            Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(id = R.string.animated_content_sample_2),
                    Modifier.padding(8.dp), style = MaterialTheme.typography.h6
                )
                Text(stringResource(id = R.string.expand), Modifier.padding(8.dp))
                Checkbox(checked = expand, onCheckedChange = { expand = !expand })
            }
            Divider()
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)) {
                if (!expand) {
                    Box(
                        Modifier
                            .wrapContentSize()

                    ) {
                        Text("short text", Modifier.padding(8.dp))
                    }
                } else {
                    Box(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        Text(
                            "Long text\nLong text line\nLong text line 3",
                            Modifier.padding(8.dp)
                        )
                    }
                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)

        ) {

            Text(stringResource(id = R.string.crossfade_animation_sample), Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6)
            Divider()


            val targetState = pick

            Box(Modifier.fillMaxSize()
                .clickable{
                    val items = Picture.values()
                    val nextItem = if(pick.ordinal < items.size - 1) items[pick.ordinal + 1] else items[0]
                    pick = nextItem
                }) {
                Text(
                    targetState.name,
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                       ,
                    fontSize = 30.sp, fontWeight = FontWeight.Bold
                )
                when (targetState) {
                    Picture.Man -> {
                        Image(painterResource(id = R.drawable.professor), null)
                    }

                    Picture.Woman -> {
                        Image(painterResource(id = R.drawable.woman), null)

                    }

                    Picture.Daughter -> {
                        Image(painterResource(id = R.drawable.daughter), null)
                    }

                    Picture.Son -> {
                        Image(painterResource(id = R.drawable.son), null)
                    }

                    Picture.All -> {
                        Row(Modifier.fillMaxSize()) {
                            val pics = listOf(
                                R.drawable.professor, R.drawable.woman,
                                R.drawable.daughter, R.drawable.son
                            )
                            for (pic in pics)
                                Image(
                                    painterResource(id = pic),
                                    null,
                                    Modifier
                                        .weight(1f)
                                        .align(Alignment.Bottom)
                                        .graphicsLayer {
                                            if (pic == R.drawable.professor) rotationY = 180f
                                        }
                                )
                        }
                    }
                }
            }

        }

    }
}
