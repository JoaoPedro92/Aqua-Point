package pt.iade.ei.aquapoint

import CreateNavBarPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.data.UserDataRepository
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseAquaPoints
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.ui.pages.CreateAddAquaPointPage
import pt.iade.ei.aquapoint.ui.pages.CreateFavoritesPage
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.pages.CreateHomePage
import pt.iade.ei.aquapoint.ui.pages.CreateLoginPage
import pt.iade.ei.aquapoint.ui.pages.CreatePersonalArea
import pt.iade.ei.aquapoint.ui.components.CreatePointDetail
import pt.iade.ei.aquapoint.ui.pages.CreateRegisterPage
import pt.iade.ei.aquapoint.ui.pages.CreateSearchPage
import pt.iade.ei.aquapoint.ui.pages.MapScreen

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
                if (UserDataRepository.getUserId() != null) {
                    LaunchedEffect(Unit) {
                        NetworkService.getFavoriteAquaPoints(UserDataRepository.getUserId()) { result ->
                            val parsed = parseAquaPoints(result)

                            AquaPointsRepository.setFavoriteCache(parsed)
                        }
                    }

                    CreateFavoritesPage(navController)
                    showNavBar.value = true
                } else {
                    CreateHomePage(navController)

                    showNavBar.value = false
                }
            }

            composable(Screen.Add.route) {
                if (UserDataRepository.getUserId() != null) {
                    CreateAddAquaPointPage(navController)
                    showNavBar.value = true
                } else {
                    CreateHomePage(navController)

                    showNavBar.value = false
                }
            }

            composable(Screen.Profile.route) {
                if (UserDataRepository.getUserId() != null) {
                    CreatePersonalArea(navController)
                    showNavBar.value = true
                } else {
                    CreateHomePage(navController)

                    showNavBar.value = false
                }
            }

            composable(Screen.Search.route) {
                LaunchedEffect(Unit) {
                    NetworkService.getFavoriteAquaPoints(UserDataRepository.getUserId()) { result ->
                        val parsed = parseAquaPoints(result)

                        AquaPointsRepository.setFavoriteCache(parsed)
                    }
                }

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