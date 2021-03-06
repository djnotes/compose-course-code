package ninja.codezombie.statedraft

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ninja.codezombie.statedraft.ui.theme.StateDraftTheme
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateDraftTheme() {
                ItemScreen()
            }
        }
    }
}


@Composable
fun ItemScreen() {
    val colors = listOf(
        Color.Black,
        Color.Gray,
        Color.Magenta,
        Color.Yellow,
        Color.LightGray,
        Color.Cyan,
        Color.Blue,
        Color.DarkGray
    )

    var count = remember{mutableStateOf(0)}
    val size = 400.dp
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .size(size)
                .background(Color.Red), contentAlignment = Alignment.Center){
                var childSize = size - 20.dp
                for (i in 0 until count.value){
                    Box(modifier = Modifier
                        .size(childSize)
                        .background(colors[i%colors.count()])
                        .border(1.dp, Color.Black)
                    )




                    childSize -= 20.dp
                }
            }
            OutlinedTextField(value = "${count.value}", onValueChange = {})
            Row {
                Button(onClick = { count.value++ }, modifier= Modifier.padding(8.dp)) {
                    Row{
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Increase")
                        Text("Increase")
                    }
                }
                Button(onClick = { count.value = if (count.value <= 0) 0 else count.value-1 }, Modifier.padding(8.dp)) {
                    Row{
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_24), contentDescription = "Delete")
                        Text("Decrease")
                    }


                }
            }

        }
}

@Preview
@Composable
fun PreviewItemScreen() {
    ItemScreen()
}

@Composable
fun pickColor(): Color {
    val colors = listOf(
        Color.Black,
        Color.Gray,
        Color.Magenta,
        Color.Yellow,
        Color.LightGray,
        Color.Cyan,
        Color.Blue,
        Color.DarkGray
    )

//    val index = Random.nextInt(0,colors.count() - 1)
    var index by remember{mutableStateOf(0)}

    return if (index < colors.count()) {val temp = colors[index]; index++; temp}
    else {index=0;colors[index]}

//    return colors[index]
}

