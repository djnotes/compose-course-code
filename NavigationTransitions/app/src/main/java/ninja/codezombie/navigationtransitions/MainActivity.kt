package ninja.codezombie.navigationtransitions

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ninja.codezombie.navigationtransitions.ui.theme.NavigationTransitionsTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
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
    Screen.Favorites,
    Screen.More,
)

@ExperimentalAnimationApi
@Preview
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    val currBackStackEntry by navController.currentBackStackEntryAsState()
    val currDest = currBackStackEntry?.destination
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
        AnimatedNavHost(navController = navController,
            startDestination = Screen.Home.route,
            enterTransition = {i,t ->
                when(t.destination.route){
                    Screen.Favorites.route -> slideInVertically(initialOffsetY = {height -> -height}, animationSpec = spring(
                        Spring.DampingRatioMediumBouncy))
                    else -> slideInHorizontally(initialOffsetX = {width -> -width}, animationSpec = tween(1000))

                }
            },
            exitTransition = {i, t ->
                slideOutHorizontally(targetOffsetX = {width -> width}, animationSpec = tween(1000))
            },
            popExitTransition = {i,t ->
                shrinkOut(shrinkTowards = Alignment.Center, targetSize = {size -> IntSize(0, size.height) }, animationSpec = tween(300))
            },
            popEnterTransition = {i,t ->
                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Up, animationSpec = tween(1000, 300))
            }
        ){
            composable(Screen.Home.route,
            exitTransition = {i,t ->
                when(t.destination.route){
                    Screen.Cart.route -> shrinkVertically(shrinkTowards = Alignment.Bottom, targetHeight = {height -> 0}, animationSpec = tween(300))
                    else -> null
                }

            }){
                MyScreen(Screen.Home.title, Screen.Home.icon)
            }
            composable(Screen.Cart.route,
            enterTransition = {i, t ->
                when(i.destination.route){
                    Screen.Home.route -> expandIn(expandFrom = Alignment.Center, initialSize = {size -> IntSize(0, size.height)}, animationSpec = tween(1000, 300))
                    else -> null
                }
            }){
                MyScreen(Screen.Cart.title, Screen.Cart.icon)
            }
            navigation(route = Screen.More.route, startDestination = Screen.Profile.route,
                enterTransition = {i,t ->
                    fadeIn(animationSpec = tween(1000))
                },
                exitTransition = {i, t ->
                    fadeOut(animationSpec = tween(1000))
                },

            ){
                composable(Screen.Profile.route){
                    MyScreen(title = Screen.Profile.title, icon = Screen.Profile.icon){
                        val ctx = LocalContext.current
                        Column(
                            Modifier
                                .align(Alignment.TopCenter)
                                .padding(8.dp)
                                ,
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

                            Column(modifier = Modifier
                                .padding(4.dp)
                                .background(Color.LightGray, RoundedCornerShape(4.dp))
                            ){
                                Text("Email: info@example.com", Modifier
                                    .padding(4.dp)
                                )
                                Text("JOHN \"GULLIBLE\" DOE\n" +
                                        "CENTER FOR FINANCIAL ASSISTANCE\n" +
                                        "421 E DRACHMAN\n" +
                                        "TUCSON AZ 85705-7598\n" +
                                        "USA\n"
                                    , Modifier
                                        .padding(4.dp))
                            }

                        }
                    }
                }
                composable(Screen.Contact.route){
                    MyScreen(Screen.Contact.title, Screen.Contact.icon)
                }
            }
            composable(Screen.Favorites.route){
                MyScreen(Screen.Favorites.title, Screen.Favorites.icon)
            }

        }
    }
}

@Composable
fun MyScreen(title: String, icon: ImageVector, extra_content: @Composable BoxScope.() -> Unit = {}) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ){
        Icon(icon, title,
            tint = Color.Red
            , modifier = Modifier
            .fillMaxSize(0.5f)
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