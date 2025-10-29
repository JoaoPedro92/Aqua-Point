package pt.iade.ei.aquapoint

import CreateNavBarPage
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.github.kittinunf.fuel.httpGet
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.components.CreateHomePage
import pt.iade.ei.aquapoint.ui.components.CreateLoginPage
import pt.iade.ei.aquapoint.ui.components.MapScreen
import pt.iade.ei.aquapoint.ui.components.Place


sealed class Screen(val route: String, val icon: ImageVector) {
    object Home : Screen("home", Icons.Filled.Home)
    object Favorite : Screen("favorite", Icons.Filled.Favorite)
    object Add : Screen("add", Icons.Filled.AddCircle)
    object Profile : Screen("profile", Icons.Filled.AccountCircle)
}