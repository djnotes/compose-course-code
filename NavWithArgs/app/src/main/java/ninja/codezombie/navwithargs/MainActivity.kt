package ninja.codezombie.navwithargs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ninja.codezombie.navwithargs.ui.theme.NavWithArgsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavWithArgsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            SelectionScreen(onNavigate = {route->
                                navController.navigate(route)
                            })
                        }

                        composable("show/{imageResource}/{scale}?caption={caption}",
                            arguments = listOf(
                                navArgument("imageResource"){type = NavType.IntType},
                                navArgument("scale"){type = NavType.FloatType},
                                navArgument("caption"){defaultValue = "Selected Image"}
                            )
                        ){ backStackEntry ->
                            val image = backStackEntry.arguments?.getInt("imageResource")
                            val scale = backStackEntry.arguments?.getFloat("scale")
                            val caption = backStackEntry.arguments?.getString("caption")
                            if (image != null && scale != null) {
                                ShowScreen(imageResource = image, scale = scale, caption = caption.toString())
                            }
                        }


                        composable("greet?name={name}",
                           arguments = listOf(
                               navArgument("name"){type = NavType.StringType; defaultValue = "John Doe"}
                           )
                        ){ backStackEntry ->
                            GreetingScreen(name = backStackEntry.arguments?.getString("name").toString())
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun SelectionScreen(onNavigate: (String) -> Unit) {
Column(modifier = Modifier
    .fillMaxSize()

) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray, RoundedCornerShape(5.dp))
        .clip(RoundedCornerShape(5.dp))
    ) {
        Text("Navigate with Arguments", style = MaterialTheme.typography.caption, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(8.dp)
        )
        val images = listOf(R.drawable.pineapple, R.drawable.apple, R.drawable.banana)
        var selectedImage by remember{ mutableStateOf(0) }


        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
        ) {
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
                                "HomeScreen: Selected Image: $selectedImage. "
                            )
                        }
                    )
                    .background(if (i == selectedImage) Color.Magenta else Color.Unspecified)
                )
            }
        }

        val (slide, onSlideChange) = remember{ mutableStateOf(0f) }
        Slider(value = slide , onValueChange = { onSlideChange(it);Log.d("MainActivity", "HomeScreen: slide: $slide") }, valueRange = 0f..3f, modifier = Modifier
            .padding(horizontal = 8.dp))

        Text("Scale: $slide", modifier = Modifier.padding(8.dp))

        val (caption, onCaptionChange) = remember{mutableStateOf("")}
        OutlinedTextField(value = caption, onValueChange = onCaptionChange, label = {
            Text("Image Caption")
        }, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
        )

        Button(onClick = {
//                         navController.navigate("show/${images[selectedImage]}/$slide?caption=$caption")
                         onNavigate("show/${images[selectedImage]}/$slide?caption=$caption")
        }, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            Text("Show Image"
            )

        }

    }

    Column(modifier = Modifier
        .weight(1f)
    ) {
        val (name, onNameChange) = remember{mutableStateOf("")}
        Text("Navigate with Optional Arguments", style = MaterialTheme.typography.caption, modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(value = name, onValueChange = onNameChange, label = {Text("Name")}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            , trailingIcon = {Icon(Icons.Filled.Person, null)}

        )

        Button(onClick = {
//                         navController.navigate("greet?name=$name")
                         onNavigate("greet?name=$name")
        }, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(8.dp)
        ) {
            Text("Greet Me", modifier = Modifier
                .padding(8.dp)
            )
        }


    }
}



}

/**
 * Composable to show the result of user's selection
 */
@Composable
fun ShowScreen(imageResource: Int, scale: Float, caption: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .shadow(2.dp)
    ) {
        Image(painter = painterResource(imageResource), contentDescription = null, contentScale = ContentScale.Inside,
            modifier = Modifier
                .scale(scale)
                .align(Alignment.Center)
        )
        Text(caption, style = MaterialTheme.typography.h2,
        modifier = Modifier
            .align(Alignment.Center)
            .padding(8.dp)
        )
    }
}

/**
 * Greet the user with the given name
 */
@Composable
fun GreetingScreen(name: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        Text("Welcome $name", style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        )
    }
}