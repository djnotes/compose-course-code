package ninja.codezombie.composelayouts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import ninja.codezombie.composelayouts.ui.theme.ComposeLayoutsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun ItemScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(300.dp)
                .weight(3f), contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .size(200.dp)
                .background(Color.Blue))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Red))

            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.TopStart))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.TopEnd))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.BottomStart))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.BottomEnd))


        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = "0", onValueChange = { /*TODO*/ }, modifier = Modifier.padding(4.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.weight(1f)) {
            Button(onClick = {}, modifier = Modifier
                .padding(4.dp)
                .weight(1f)) {
                Text("Increase")
            }

            Button(onClick = {}, modifier = Modifier
                .padding(4.dp)
                .weight(1f)) {
                Text("Decrease")
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
fun ConstraintItemScreen() {
    val constraints = ConstraintSet{
        val box = createRefFor("box")
        constrain(box){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }

        val input = createRefFor("input")
        val increase = createRefFor("increase")
        val decrease = createRefFor("decrease")

        constrain(input){
            top.linkTo(box.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(increase) {
            start.linkTo(parent.start)
            top.linkTo(input.bottom)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            end.linkTo(decrease.start)
        }

        constrain(decrease){
            top.linkTo(increase.top)
            end.linkTo(parent.end)
            bottom.linkTo(increase.bottom)
            width = Dimension.fillToConstraints
            start.linkTo(increase.end)
        }

    }
    ConstraintLayout(modifier = Modifier.fillMaxHeight(), constraintSet = constraints){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(500.dp)
                .layoutId("box")
                , contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier
                .size(200.dp)
                .background(Color.Blue))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Red))

            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.TopStart))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.TopEnd))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.BottomStart))
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .align(Alignment.BottomEnd))


        }


        OutlinedTextField(value = "0", onValueChange = { /*TODO*/ },
            modifier = Modifier.padding(4.dp)
                .layoutId("input")
        )


        Button(onClick = {}, modifier = Modifier
            .padding(4.dp)
            .layoutId("increase")
            ) {
            Text("Increase")
        }

        Button(onClick = {}, modifier = Modifier
            .padding(4.dp)
            .layoutId("decrease")
            ) {
            Text("Decrease")
        }


    }
}

@Preview
@Composable
fun PreviewConstraintItemScreen() {
    ConstraintItemScreen()
}