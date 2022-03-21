package ninja.codezombie.nestednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ninja.codezombie.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedNavigationTheme {
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
    NavHost(navController, startDestination = "A"){
        composable("A"){
            Screen(it.destination.route.toString()){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = { navController.navigate("B") },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Go To Screen B")
                    }

                    Button(
                        onClick = { navController.navigate("C") },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Go To Screen C")
                    }
                }
            }
        }

        navigation(route = "B",
        startDestination = "BA"){
            composable("BA"){
                Screen(it.destination.route.toString()){
                    Column (modifier = Modifier
                        .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Button(onClick = { navController.navigate("BB") }, modifier = Modifier.padding(16.dp)) {
                            Text("Go To BB")
                        }

                        Button(onClick = { navController.navigate("BC") }, modifier = Modifier.padding(16.dp)) {
                            Text("Go To BC")
                        }


                        Button(onClick = { navController.navigate("BD")}, modifier = Modifier.padding(16.dp)) {
                            Text("Go To BD")
                        }
                    }
                }
            }

            composable("BB"){
                Screen(it.destination.route.toString())
            }

            composable("BC"){
                Screen(it.destination.route.toString())
            }

            composable("BD"){
                Screen(it.destination.route.toString())
            }

        }

        composable("C"){
            Screen("C"){}
        }
    }
}


@Composable
fun Screen(label: String, content: @Composable () -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxSize()
    ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 50.sp, textAlign = TextAlign.Center, modifier = Modifier
            .padding(16.dp)
        )

        //Invoke the passed content here
        content()
    }
}