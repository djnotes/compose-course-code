package ninja.codezombie.navigationtransitions

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ninja.codezombie.navigationtransitions.ui.theme.NavigationTransitionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTransitionsTheme {
                MainScreen()
            }
        }
    }
}


val screens = listOf(
    Screen.Home,
    Screen.Cart,
    Screen.More,
)

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController() //TODO 1: Replace with rememberAnimatedNavController
    val currDest = remember{navController.currentBackStackEntry?.destination}
    Scaffold(bottomBar = {
        BottomNavigation{
            screens.forEach {screen->
                BottomNavigationItem(
                    selected =  currDest?.hierarchy?.any { dest -> dest.route == screen.route } == true ,
                    onClick = {
                              navController.navigate(screen.route){
                                  popUpTo(navController.graph.findStartDestination().id){
                                      saveState = true
                                  }

                                  restoreState = true
                                  launchSingleTop = true
                              }
                    },
                    icon = { Icon(screen.icon, screen.title) },
                    label = {Text(screen.title)}
                )
            }
        }
    }){
        NavHost(navController = navController,
            startDestination = Screen.Home.route
        ){ //TODO 2: Replace with AnimatedNavHost
            composable(Screen.Home.route){ //TODO 3: Replace with com.google.accompanist.navigation.animation.composable
                MyScreen(Screen.Home.title, Screen.Home.icon)
            }
            composable(Screen.Cart.route){
                MyScreen(Screen.Cart.title, Screen.Cart.icon)
            }
            navigation(route = Screen.More.route, startDestination = Screen.Profile.route){ //TODO 4: Replace navigation with com.google.accompanist.navigation.animation.navigation
                composable(Screen.Profile.route){
                    MyScreen(title = Screen.Profile.title, icon = Screen.Profile.icon){
                        val ctx = LocalContext.current
                        Column(Modifier
                            .align(Alignment.TopCenter)
                            .padding(8.dp)
                            .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(onClick = {
                                navController.navigate(Screen.Contact.route)
                            },
                                modifier = Modifier
                                    .padding(8.dp)
                            ) {
                                Text("Email")
                            }

                            Button(modifier = Modifier
                                .padding(8.dp),
                                onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply{
                                    data = "https://example.com".toUri()
                                }
                                startActivity(ctx, intent, null)

                            }){
                                Text("Website")
                            }
                        }
                    }
                }
                composable(Screen.Contact.route){
                    MyScreen(Screen.Contact.title, Screen.Contact.icon)
                }
            }
            composable(Screen.Favorites.route){
                MyScreen(Screen.Favorites.title, Screen.Favorites.icon){
                    Column(modifier = Modifier
                        .align(Alignment.TopCenter)){
                        Text("Email: info@example.com", Modifier
                            .padding(8.dp)
                        )
                        Text("   JOHN \"GULLIBLE\" DOE\n" +
                                "   CENTER FOR FINANCIAL ASSISTANCE\n" +
                                "   421 E DRACHMAN\n" +
                                "   TUCSON AZ 85705-7598\n" +
                                "   USA\n"
                            , Modifier
                                .padding(8.dp))
                    }
                }
            }

        }
    }
}

@Composable
fun MyScreen(title: String, icon: ImageVector, extra_content: @Composable BoxScope.() -> Unit = {}) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ){
        Icon(icon, title, Modifier
            .fillMaxSize(0.3f)
            .align(Alignment.Center)
        )
        Text(title, style = MaterialTheme.typography.h2, fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .padding(bottom = 100.dp)
        )

        extra_content()
    }
}