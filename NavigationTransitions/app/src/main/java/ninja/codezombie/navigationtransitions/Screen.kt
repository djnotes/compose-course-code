package ninja.codezombie.navigationtransitions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector){
    object Home:Screen("home", "Home", Icons.Outlined.Home)
    object Profile: Screen("profile", "Profile", Icons.Outlined.Person)
    object Cart: Screen("cart", "Cart", Icons.Outlined.ShoppingCart)
    object Favorites: Screen("favorites", "Favorites", Icons.Outlined.FavoriteBorder)
    object More: Screen("more", "More", Icons.Outlined.MoreVert)
    object Contact: Screen("contact", "Contact", Icons.Outlined.Email)
}
