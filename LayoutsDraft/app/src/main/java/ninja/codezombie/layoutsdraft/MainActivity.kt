package ninja.codezombie.layoutsdraft

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ninja.codezombie.layoutsdraft.ui.theme.LayoutsDraftTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsDraftTheme {
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//                MainScreen()
                ShoppingCart()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutsDraftTheme {
        Greeting("Android")
    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

@Composable
fun MainScreen() {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .background(Color.Magenta)
            .fillMaxWidth()
            .weight(3f))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.padding(8.dp), label = {Text("The Label")}, )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.weight(1f)) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(4.dp).weight(1f)) {
                Text("Increase")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(4.dp).weight(1f)) {
                Text("Decrease")
            }
        }
    }
}

@Composable
fun ShoppingCart() {
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)){
        val (incr, decr) = createRefs()

        val box = createRef()

        val input = createRef()

        Box(modifier = Modifier
            .background(Color.Magenta)
            .size(200.dp)
            .constrainAs(box) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
        OutlinedTextField(value = "", onValueChange = {},
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(input) {
                    top.linkTo(box.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            , label = {Text("The Label")}, )

            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(incr) {
                        start.linkTo(parent.start)
                        top.linkTo(input.bottom)
                    }
            ) {
                Text("Increase")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(decr) {
                        start.linkTo(incr.end)
                        top.linkTo(incr.top)
                        end.linkTo(parent.end)
                    }
            ) {
                Text("Decrease")
            }
    }
}