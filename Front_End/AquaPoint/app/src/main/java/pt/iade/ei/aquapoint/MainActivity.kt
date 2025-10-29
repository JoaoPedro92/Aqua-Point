package pt.iade.ei.aquapoint

import CreateNavBarPage
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.kittinunf.fuel.httpGet
import pt.iade.ei.aquapoint.ui.components.CreateAddAquaPointPage
import pt.iade.ei.aquapoint.ui.components.CreateFavoritesPage
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.components.CreateHomePage
import pt.iade.ei.aquapoint.ui.components.CreateLoginPage
import pt.iade.ei.aquapoint.ui.components.CreatePersonalArea
import pt.iade.ei.aquapoint.ui.components.MapScreen
import pt.iade.ei.aquapoint.ui.components.Place


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AquaPointTheme {
                val navController = rememberNavController()

                val places = listOf(
                    Place("Posto 1 - Santa Maria", "15min -3.8km", 4, R.drawable.aqua_point_logo, latitude = 38.78049309176663, longitude = -9.102677320690447),
                    Place("Posto 2 - Moscavide", "25min - 5km", 3, R.drawable.aqua_point_logo, latitude = 38.78105763775865, longitude = -9.103412245980696),
                    Place("Posto 3 - Santos", "35min - 7km", 3, R.drawable.aqua_point_logo, latitude = 38.77977799376074, longitude = -9.103589271780537),
                    Place("Posto 4 - Lumiar", " 10min - 2km", 3, R.drawable.aqua_point_logo, latitude = 38.78053909197767, longitude =  -9.101379131491612)
                )

                LoadHomePage(navController, places)
            }
        }
    }
}

@Composable
fun LoadHomePage(navController: NavHostController, places: List<Place>) {
    Box(modifier = Modifier.fillMaxSize()) {

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Screen.Home.route) { MapScreen(places) }
            composable(Screen.Favorite.route) { CreateFavoritesPage() }
            composable(Screen.Add.route) { CreateAddAquaPointPage() }
            composable(Screen.Profile.route) { CreatePersonalArea() }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 30.dp
                )
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            CreateNavBarPage(
                items = listOf(
                    Screen.Add,
                    Screen.Favorite,
                    Screen.Home,
                    Screen.Profile
                ),
                navController = navController
            )
        }
    }
}
