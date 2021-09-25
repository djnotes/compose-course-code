package ninja.codezombie.composedeeplink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import ninja.codezombie.composedeeplink.ui.theme.ComposeDeepLinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDeepLinkTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyScreens()
                }
            }
        }
    }
}


@Composable
fun MyScreens() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home"){
        composable("home"){
            HomeScreen()
        }

        composable("greeting/{name}",
        arguments = listOf(navArgument("name"){}),
        deepLinks = listOf(navDeepLink{uriPattern = "myapp://greeting/{name}"})
        ){
            GreetingScreen(it.arguments?.getString("name").toString())
        }
    }
}

@Composable
fun GreetingScreen(name: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)
    ){
        Text("Hello $name", style = MaterialTheme.typography.h3, modifier = Modifier
            .padding(16.dp)
        )
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .fillMaxSize()

    ){
        Text("Welcome To Home Screen", fontSize = 30.sp,
        textAlign = TextAlign.Center, modifier = Modifier
                .align(Alignment.Center)
        )
    }
}


