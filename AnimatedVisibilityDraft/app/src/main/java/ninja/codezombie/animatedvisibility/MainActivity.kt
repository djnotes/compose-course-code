package ninja.codezombie.animatedvisibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ninja.codezombie.animatedvisibility.ui.theme.AnimatedVisibilityTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedVisibilityTheme {
                AnimatedVisibilityMotion()
            }
        }
    }
}


@Preview
@ExperimentalAnimationApi
@Composable
fun AnimatedVisibilityMotion() {
    val (checked, onCheckedChange) = remember{mutableStateOf(true)}

    val show = remember{
        MutableTransitionState(false).apply{
            targetState = true
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .shadow(2.dp)
            ) {

        Row(Modifier.padding(16.dp)) {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            Text("Show Box")
        }
        Spacer(Modifier.height(50.dp))



        AnimatedVisibility(visibleState = show,
        enter = slideInVertically(animationSpec = tween(durationMillis = 1000), initialOffsetY= {-2*it}),
        exit =  slideOutHorizontally(animationSpec = tween(1000), targetOffsetX = {it+it/2})) {
            Box(
                Modifier
                    .size(400.dp)
                    ){
                Image(painterResource(R.drawable.profile), "Profile", Modifier.padding(16.dp)
                    .clip(RoundedCornerShape(6.dp)).border(4.dp, Color.Red), contentScale = ContentScale.Crop)
                Text("Jetpack Compose is awesome",  Modifier.align(Alignment.BottomCenter).padding(vertical = 16.dp)
                    .animateEnterExit(enter = slideInVertically(initialOffsetY = {2*it},
                        animationSpec = tween(1000, easing = LinearEasing)), exit = shrinkVertically(shrinkTowards = Alignment.Top) + slideOutVertically(targetOffsetY = {it/2}) + fadeOut()), fontSize = 30.sp, color = Color.White, textAlign = TextAlign.Center)
            }




        }

    }
}