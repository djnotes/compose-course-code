package ninja.codezombie.animatedvisibilitymotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ninja.codezombie.animatedvisibilitymotion.ui.theme.AnimatedVisibilityMotionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedVisibilityMotionTheme {
                AnimatedVisibilitySample()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimatedVisibilitySample() {
    val (checked, onCheckedChange) = remember{mutableStateOf(false)}


    val show = remember{
        MutableTransitionState(false).apply{
            targetState = true
        }
    }
    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(2.dp)){
        Row(Modifier.padding(16.dp)) {
            Checkbox(checked, onCheckedChange, enabled = false)
            Text("Show Content")
        }

        AnimatedVisibility(visibleState = show,
        enter = fadeIn() + slideInHorizontally(initialOffsetX = {-it},
            animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, visibilityThreshold = IntOffset(200, 100))),
        exit = shrinkOut(shrinkTowards = Alignment.BottomStart,
        animationSpec = tween(1000))) {
            Box(
                Modifier
                    .size(400.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.BottomCenter
            ){
                Image(painterResource(id = R.drawable.profile), "Profile", modifier = Modifier.clip(
                    RoundedCornerShape(8.dp)
                )
                    .fillMaxSize(),
                    contentScale = ContentScale.Crop
                    )
                Text("Jetpack Compose is awesome", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White,
                    textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter)
                    .animateEnterExit(enter = slideInVertically(initialOffsetY = {2*it}, tween(1000)))
                )
            }
        }
    }
}