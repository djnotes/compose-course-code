package ninja.codezombie.composebottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String){
    object Home: Screen("home", Icons.Outlined.Home, "Home")
    object Profile: Screen("profile", Icons.Outlined.Person, "Profile")
    object Notifications: Screen("notifications", Icons.Outlined.Notifications, "Notifications")
}

