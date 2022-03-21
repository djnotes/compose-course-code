package ninja.codezombie.jetpackcomposeimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import ninja.codezombie.jetpackcomposeimage.ui.theme.JetpackComposeImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkImage()
        }
    }
}


@Composable
fun ComposeImages() {

    Column (Modifier.fillMaxSize()){
        Text("Vector Graphic")
        Image(painter = painterResource(id = R.drawable.android), contentDescription = "Android(TM) Bot", Modifier.fillMaxWidth())

        Text("Bitmap Graphic")
        Spacer(Modifier.height(64.dp))
        Image(painter = painterResource(id = R.drawable.flower), contentDescription = "Flower Content Description",
            Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(CircleShape), contentScale = ContentScale.Crop)
    }

}

//@Preview
@Composable
fun ComposeImagesPreview() {
    ComposeImages()
}

@Composable
fun NetworkImage() {
    Column{
        CoilImage(data = "https://picsum.photos/400/400", contentDescription = "Network Image", fadeIn = true)
    }
}

@Preview
@Composable
fun NetworkImagePreview() {
    NetworkImage()
}