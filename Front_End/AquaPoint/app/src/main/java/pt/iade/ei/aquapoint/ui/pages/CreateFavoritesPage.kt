package pt.iade.ei.aquapoint.ui.pages

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import pt.iade.ei.aquapoint.ui.components.CreateFilteredAquaPointByName
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme


@Composable
fun CreateFavoritesPage(navController: NavHostController){
    AquaPointTheme {
        CreateFilteredAquaPointByName(
            navController = navController,
            isFavorite = true
        )


       /* val places = AquaPointsRepository.getCached() ?: emptyList()

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            CreateSearchBox()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            )
            {
                items(places) { place ->
                    CreatePointCard(
                        place = place,
                        isFavorite = true,
                        onClick = {
                            navController.navigate(Screen.Detail.createRoute(place.id))
                        }
                    )
                }
            }

            //CreateNavBarPage()
        }*/
    }
}

