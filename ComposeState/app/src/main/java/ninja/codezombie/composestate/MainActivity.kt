package ninja.codezombie.composestate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ninja.codezombie.composestate.ui.theme.ComposeStateTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (count, onCountChange) = remember{mutableStateOf(0)}
            BoxScreen(count) {change -> if (count + change <= 0) onCountChange(0) else onCountChange(count + change) }
        }
    }
}

@Composable
fun BoxScreen(count: Int, onCountChange: (Int) -> Unit) {
    val boxSize = 400.dp
//    var count by remember{mutableStateOf(0)}
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(Color.Red)
        ,
            contentAlignment = Alignment.Center
        ){
            var childSize = boxSize - 20.dp
            for(i in 0 until count){
                Box(modifier = Modifier.size(childSize).rotate(i*3f)
                    .background(Color.Gray).border(1.dp, Color.Black))
                childSize -= 20.dp
            }
        }

        OutlinedTextField(value = "$count", onValueChange = { /*TODO*/ })

        Row() {
            Button(onClick = { onCountChange(1) }, Modifier.padding(8.dp)) {
                Text("Increase")
            }

            Button(onClick = { onCountChange(-1) }, Modifier.padding(8.dp)) {
                Text("Decrease")
            }
        }
    }
}

@Preview
@Composable
fun PreviewBoxScreen() {
    var count by remember{mutableStateOf(0)}
    BoxScreen(count) {change -> count = if (count + change <= 0) 0 else count + change }
}