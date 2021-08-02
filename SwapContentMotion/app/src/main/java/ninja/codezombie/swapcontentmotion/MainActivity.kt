package ninja.codezombie.swapcontentmotion


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

enum class Pictures{Man, Woman, Daughter, Son, All}

@Preview
@Composable
fun AnimationDemo() {
    var count by remember{ mutableStateOf(0)}
    val expand by remember{ mutableStateOf(false)}

    /**
     * Enum variable to select a picture to crossfade to
     */
    var pick by remember{mutableStateOf(Pictures.Man)}


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(2.dp)) {
        Column(
            Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(stringResource(id = R.string.animated_content_sample_1), Modifier.padding(8.dp))
            Divider()
            IconButton(onClick = {count++}) {
                Icon(painterResource(id = R.drawable.up_direction), null, Modifier
                    .size(100.dp))
            }

            Box(
                Modifier
                    .height(100.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.Center){
                Text("$count", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Red)
            }

            IconButton(onClick = { if (count > 0) count-- }) {
                Icon(painterResource(id = R.drawable.down_direction), null, Modifier
                    .size(100.dp))
            }

        }

        Column(
            Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .padding(8.dp)) {

            Text(stringResource(id = R.string.animated_content_sample_2), Modifier.padding(8.dp))
            Divider()
            if (expand){
                Box(
                    Modifier
                        .wrapContentSize()
                        .background(Color.Cyan)){
                    Text("short text", Modifier.padding(8.dp))
                }
            }
            else{
                Box(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(Color.Cyan)){
                    Text("Long text\n Long text line \n Long text line 3", Modifier.padding(8.dp))
                }
            }

        }

        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray)) {

            Text(stringResource(id = R.string.crossfade_animation_sample), Modifier.padding(8.dp))
            Divider()

            Crossfade(targetState = pick, Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    val items = Pictures.values()
                    val newIndex = if (pick.ordinal < items.size - 1) pick.ordinal + 1 else 0
                    pick = items[newIndex]
                },
                animationSpec = spring()

            ) {targetState ->
                when(targetState){
                    Pictures.Man -> {
                        Image(painterResource(id = R.drawable.professor), null)
                    }

                    Pictures.Woman -> {
                        Image(painterResource(id = R.drawable.woman), null)

                    }

                    Pictures.Daughter -> {
                        Image(painterResource(id = R.drawable.daughter), null)
                    }

                    Pictures.Son -> {
                        Image(painterResource(id = R.drawable.son), null)
                    }

                    Pictures.All -> {
                        Row(Modifier.fillMaxSize().background(Color.Yellow)) {
                            val pics = listOf(R.drawable.professor, R.drawable.woman,
                            R.drawable.daughter, R.drawable.son)
                            for (pic in pics)
                                Image(painterResource(id = pic), null, Modifier.fillMaxWidth(0.25f).align(Alignment.Bottom))
                        }
                    }
                }
            }

        }

    }
}