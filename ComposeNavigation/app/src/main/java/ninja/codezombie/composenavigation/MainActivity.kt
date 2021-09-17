package ninja.codezombie.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        verticalArrangement = Arrangement.Center
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

        Text("Home Screen", style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
        )
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