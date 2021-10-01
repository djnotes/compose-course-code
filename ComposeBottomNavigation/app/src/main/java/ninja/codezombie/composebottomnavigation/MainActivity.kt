package ninja.codezombie.composebottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.composable
import ninja.codezombie.composebottomnavigation.ui.theme.ComposeBottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomNavigationTheme {
                MainScreen()
            }
        }
    }
}


val mainScreens = listOf(
    Screen.Home,
    Screen.Profile,
    Screen.Notifications,
    Screen.More
)

@Preview
@Composable
fun MainScreen() {
    val nc = rememberNavController()

    Scaffold(
        topBar = {TopAppBar(title = {Text(stringResource(R.string.app_name))})},
        bottomBar = {MyBottomBar(nc)}
    ) { padding ->
        NavHost(navController = nc, startDestination = Screen.Home.route, modifier = Modifier.padding(padding)){
            composable(Screen.Home.route){
                HomeScreen()
            }

            composable(Screen.Profile.route){
                ProfileScreen()
            }

            composable(Screen.Notifications.route){
                NotificationsScreen()
            }
            
            navigation(route = Screen.More.route, startDestination = "more-home"){
                composable("more-home"){
                   MoreScreen(nc)
                }

                composable(Screen.Gallery.route){
                    GalleryScreen()
                }

                composable(Screen.Settings.route){
                    SettingsScreen()
                }

            }
        }

    }
}

@Composable
fun SettingsScreen() {
    Placeholder(title = Screen.Settings.title, icon = Screen.Settings.icon)
}

@Composable
fun GalleryScreen() {
    Placeholder(title = Screen.Gallery.title, icon = Screen.Gallery.icon)
}

@Composable
fun MoreScreen(nc: NavHostController) {
    val subScreens = listOf(
        Screen.Gallery,
        Screen.Settings
    )
    Placeholder(title = Screen.More.title, icon = Screen.More.icon){
        for(screen in subScreens){
            Button(onClick = {
                nc.navigate(screen.route)
            }, modifier = Modifier.padding(16.dp)) {
                Text(screen.title)
            }
        }
    }
}

@Composable
fun NotificationsScreen() {
    Placeholder(title = Screen.Notifications.title, icon = Screen.Notifications.icon)
}

@Composable
fun ProfileScreen() {
    Placeholder(title = Screen.Profile.title, icon = Screen.Profile.icon)
}

@Composable
fun HomeScreen() {
    Placeholder(title = Screen.Home.title, icon = Screen.Home.icon)
}

@Composable
fun Placeholder(title: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit = {}) {
    Column(modifier = Modifier
        .fillMaxSize()
    ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(title, style = MaterialTheme.typography.h3, textAlign = TextAlign.Center, modifier = Modifier.padding(16.dp))

        Icon(icon, title, modifier = Modifier.fillMaxSize(0.3f))

        content()

    }
}

@Composable
fun MyBottomBar(nc: NavHostController) {
    val currentBackStackEntry by nc.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    BottomNavigation {
        for(screen in mainScreens){
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route} == true,
                onClick = {
                         nc.navigate(screen.route){
                             popUpTo(nc.graph.findStartDestination().id){
                                 saveState = true
                             }

                             launchSingleTop = true

                             restoreState = true
                         }
                },
                icon =  {Icon(screen.icon, screen.title)},
                label = {Text(screen.title)}
            )
        }
    }
}
