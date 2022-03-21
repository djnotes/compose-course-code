package ninja.codezombie.animatecontentsizecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ninja.codezombie.animatecontentsizecompose.ui.theme.AnimateContentSizeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateContentSizeComposeTheme {
                AnimateContentSizeDemo()
            }
        }
    }
}

@Composable
fun AnimateContentSizeDemo() {

    var small by remember{ mutableStateOf(true)}

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
        .toggleable(value = small, onValueChange = { newValue -> small = newValue })

    ){
        Image(painter = painterResource(R.drawable.italy), contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .align(Alignment.Center)
            .fillMaxSize(if (small) 0.3f else 0.9f)
            .animateContentSize()

        )
    }
}