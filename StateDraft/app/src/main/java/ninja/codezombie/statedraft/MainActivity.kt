package ninja.codezombie.statedraft

import android.app.Application
import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ninja.codezombie.statedraft.ui.theme.StateDraftTheme
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateDraftTheme() {
//                val countViewModel = ViewModelProvider.AndroidViewModelFactory(Application()).create(CountViewModel::class.java)
                val countViewModel: CountViewModel = viewModel()
                val count by countViewModel.count.observeAsState(0)
                ItemScreen(count) { change -> countViewModel.onCountChange(change) }
            }
        }
    }
}


@Composable
fun ItemScreen(count: Int, onCountChange: (change: Int) -> Unit) {
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
    val size = 400.dp
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .size(size)
                .background(Color.Red), contentAlignment = Alignment.Center){
                var childSize = size - 20.dp
                for (i in 0 until count){
                    Box(modifier = Modifier
                        .size(childSize)
                        .rotate(0f + 3f * i)
                        .shadow(5.dp)
//                        .background(colors[i % colors.count()])
                        .background(Color.Gray)
                        .border(1.dp, Color.Black)
                    )
                    childSize -= 20.dp
                }
            }
            OutlinedTextField(value = "$count", onValueChange = {})
            Row {
                Button(onClick = { onCountChange(1) }, modifier= Modifier.padding(8.dp)) {
                    Row{
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Increase")
                        Text("Increase")
                    }
                }
                Button(onClick = { onCountChange(-1) }, Modifier.padding(8.dp)) {
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
//    var count by remember{mutableStateOf(0)}
    val countViewModel: CountViewModel = viewModel()
    val count by countViewModel.count.observeAsState(0)
    ItemScreen(count) { change -> countViewModel.onCountChange(change) }
}


