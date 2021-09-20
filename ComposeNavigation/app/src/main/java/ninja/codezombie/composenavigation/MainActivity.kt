package ninja.codezombie.composenavigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ninja.codezombie.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                MyScreens()
            }
        }
    }
}

@Composable
fun MyScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController)
        }

        composable("b_route"){
            B_Screen()
        }

        composable("c_route"){
            C_Screen(navController)
        }

        composable("d_route"){
            D_Screen()
        }

        composable("e_route"){
            E_Screen()
        }


    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Button(onClick = {navController.navigate("b_route")},
        modifier = Modifier
            .padding(8.dp)
        ) {
            Text("Screen B")
        }

        Button(onClick = { navController.navigate("c_route") },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text("Screen C")
        }

        Text("Home Screen", style = MaterialTheme.typography.h6,
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)

        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
            ) {
                val images = listOf(R.drawable.pineapple, R.drawable.apple, R.drawable.banana)
                var selectedImage by remember{mutableStateOf(0)}
                for(i in images.indices){
                    val image = images[i]
                    Image(painterResource(id = image), contentDescription = null, modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .selectable(
                            selected = selectedImage == i,
                            onClick = {
                                selectedImage = i
                                Log.d(
                                "MainActivity",
                                "HomeScreen: Selected Image: $selectedImage. ")
                            }
                        )
                        .background(if  (i == selectedImage) Color.Magenta else Color.Unspecified)
                    )
                }
            }

            val (slide, onSlideChange) = remember{ mutableStateOf(0f)}
            Slider(value = slide , onValueChange = { onSlideChange(it);Log.d("MainActivity", "HomeScreen: slide: $slide") }, valueRange = 0f..3f, modifier = Modifier
                .padding(horizontal = 8.dp))
            Button(onClick = {
                //TODO: Pass image and scale as arguments to the show route
            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
            ) {
                Text("Show Image")
            }


        }


    }
}


@Composable
fun B_Screen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)
    ){
        Text("Screen B", style = MaterialTheme.typography.h1,
        modifier = Modifier
            .align(Alignment.Center)
        )
    }
}

@Composable
fun C_Screen(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = { navController.navigate("d_route") },
        modifier = Modifier
            .padding(8.dp)
        ) {
            Text("Screen D")
        }

        Button(onClick = { navController.navigate("e_route") },
        modifier = Modifier
            .padding(8.dp)
        ) {
            Text("Screen E")
        }

        Button(onClick = { navController.navigate("home") },
        modifier = Modifier
            .padding(8.dp)
        ) {
            Text("Home")
        }

        Text("Screen C", style = MaterialTheme.typography.h1)

    }
}


@Composable
fun D_Screen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Magenta)
    ){
        Text("Screen D", style = MaterialTheme.typography.h1,
        modifier = Modifier
            .align(Alignment.Center)
        )
    }
}


@Composable
fun E_Screen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)
    ){
        Text("Screen E", style = MaterialTheme.typography.h1,
        modifier = Modifier
            .align(Alignment.Center)
        )
    }
}


