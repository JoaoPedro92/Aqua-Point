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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.serialization.Serializable
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.data.UserDataRepository
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseAquaPoints
import pt.iade.ei.aquapoint.ui.components.CreateAddAquaPointPage
import pt.iade.ei.aquapoint.ui.components.CreateFavoritesPage
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.components.CreateHomePage
import pt.iade.ei.aquapoint.ui.components.CreateLoginPage
import pt.iade.ei.aquapoint.ui.components.CreatePersonalArea
import pt.iade.ei.aquapoint.ui.components.CreatePointDetail
import pt.iade.ei.aquapoint.ui.components.CreateRegisterPage
import pt.iade.ei.aquapoint.ui.components.CreateSearchPage
import pt.iade.ei.aquapoint.ui.components.MapScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AquaPointTheme {
                val navController = rememberNavController()
                var places by remember { mutableStateOf<List<AquaPoint>>(emptyList()) }

                LaunchedEffect(Unit) {
                    // só chama a API se ainda não tiver cache
                    if (!AquaPointsRepository.hasCache()) {
                        NetworkService.getAquaPoints { result ->
                            val parsed = parseAquaPoints(result)

                            AquaPointsRepository.setCache(parsed)

                            places = parsed
                        }
                    } else {
                        places = AquaPointsRepository.getCached() ?: emptyList()
                    }
                }

                LoadHomePage(navController, places)
            }
        }
    }
}

@Composable
fun LoadHomePage(navController: NavHostController, places: List<AquaPoint>) {
    val showNavBar = remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Screen.Home.route) {
                MapScreen(places, navController)
                showNavBar.value = true
            }

            composable(Screen.Favorite.route) {
                CreateFavoritesPage(navController)
                showNavBar.value = true
            }

            composable(Screen.Add.route) {
                if (UserDataRepository.getUserId() != null) {
                    CreateAddAquaPointPage()
                    showNavBar.value = true
                } else {
                    CreateHomePage(navController)

                    showNavBar.value = false
                }
            }

            composable(Screen.Profile.route) {
                if (UserDataRepository.getUserId() != null) {
                    CreatePersonalArea()
                    showNavBar.value = true
                } else {
                    CreateHomePage(navController)

                    showNavBar.value = false
                }
            }

            composable(Screen.Search.route) {
                CreateSearchPage(navController)
                showNavBar.value = true
            }

            composable(Screen.Login.route) {
                CreateLoginPage(navController)
                showNavBar.value = false
            }

            composable(Screen.Register.route) {
                CreateRegisterPage(navController)
                showNavBar.value = false
            }

            composable(Screen.MainPage.route) {
                CreateHomePage(navController)
                showNavBar.value = false
            }

            composable(Screen.Detail.route) {
                backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                CreatePointDetail(null, id, navController)
                showNavBar.value = true
            }
        }

        if (showNavBar.value) {
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
}