package pt.iade.ei.aquapoint

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String, val icon: ImageVector? = null) {
    object Home : Screen("home", Icons.Filled.Home)
    object Favorite : Screen("favorite", Icons.Filled.Favorite)
    object Add : Screen("add", Icons.Filled.AddCircle)
    object Profile : Screen("profile", Icons.Filled.AccountCircle)
    object Search : Screen("search")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int): String = "detail/$id"
    }
    object Login : Screen("login")
    object Register : Screen("register")
    object MainPage : Screen("mainPage")
}