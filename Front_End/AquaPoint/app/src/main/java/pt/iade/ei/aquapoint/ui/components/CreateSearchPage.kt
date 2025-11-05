package pt.iade.ei.aquapoint.ui.components

import CreateNavBarPage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme


@Composable
fun CreateSearchPage(navController: NavHostController){
    AquaPointTheme {
        CreateFilteredAquaPointByName(
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

