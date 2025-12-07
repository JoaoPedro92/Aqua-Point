package pt.iade.ei.aquapoint.ui.pages

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import pt.iade.ei.aquapoint.ui.components.CreateFilterAquaPoint
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme


@Composable
fun CreateSearchPage(navController: NavHostController){
    AquaPointTheme {
        CreateFilterAquaPoint(
            navController = navController,
            isFavorite = false
        )

       /* var searchText by remember { mutableStateOf("") }
        var places by remember { mutableStateOf(AquaPointsRepository.getCached() ?: emptyList()) }


        // Pega dados do backend assim que a página abrir
        LaunchedEffect(Unit) {
            AquaPointsRepository.fetchFromBackend { updatedPoints ->
                places = updatedPoints
            }
        }

        val filteredPlaces = remember(searchText, places) {
            if (searchText.isBlank()) places
            else places.filter { it.name.contains(searchText, ignoreCase = true) }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            CreateSearchBox(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                enabled = true
            )


            Spacer(modifier = Modifier.height(10.dp))

            if (filteredPlaces.isEmpty() && searchText.isNotBlank()) {
                Text(
                    text = "Bebedouro não encontrado",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .wrapContentSize(Alignment.TopCenter)
                        .padding(top = 300.dp)
                )

            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                )
                {
                    items(filteredPlaces) { place ->
                        CreatePointCard(
                            place = place,
                            isFavorite = false,
                            onClick = {
                                navController.navigate(Screen.Detail.createRoute(place.id))
                            }
                        )
                    }
                }
            }

        }*/
    }
}

