package ninja.codezombie.animatedcontentmotion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ninja.codezombie.animatedcontentmotion.ui.theme.AnimatedContentMotionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedContentMotionTheme {
                AnimationDemo()
            }
        }
    }
}


@Preview
@Composable
fun AnimationDemo() {
    var count by remember{ mutableStateOf(0)}
    val expand by remember{ mutableStateOf(false)}

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(2.dp)) {
        Column(
            Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(16.dp)) {

            IconButton(onClick = {count++}) {
                Icon(painterResource(id = R.drawable.up_direction), null)
            }
            
            Box(Modifier.size(150.dp)){
                Text("$count", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }

            IconButton(onClick = { if (count > 0) count-- }) {
                Icon(painterResource(id = R.drawable.down_direction), null)
            }
            
        }

        Column(Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(16.dp)) {

            if (expand){
                Box(Modifier.wrapContentSize().background(Color.Cyan)){
                    Text("short text")
                }
            }
            else{
                Box(Modifier.wrapContentHeight().fillMaxWidth().background(Color.Cyan)){
                    Text("Long text\n Long text line \n Long text line 3")
                }
            }

        }

    }
}