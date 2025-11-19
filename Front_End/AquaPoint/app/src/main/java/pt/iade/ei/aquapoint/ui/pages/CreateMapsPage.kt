package pt.iade.ei.aquapoint.ui.pages

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.ui.components.CreatePointDetailButtonSheet
import pt.iade.ei.aquapoint.ui.components.CreateSearchBox
import pt.iade.ei.aquapoint.ui.theme.AquaGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(places: List<AquaPoint>, navController: NavHostController) {
    val moscavide = LatLng(38.78166399699406, -9.102570032326907)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(moscavide, 17f)
    }

    // Estado do BottomSheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedPlace by remember { mutableStateOf<AquaPoint?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // --- Mapa ---
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            for (place in places) {
                val markerState = rememberMarkerState(
                    position = LatLng(place.latitude, place.longitude)
                )

                var finalColor: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(200f)

                if (place.state_id == 2) {
                    finalColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                }

                MarkerInfoWindow(
                    state = markerState,
                    title = place.name,
                    /*snippet = place.distance,*/
                    icon = finalColor,
                    onClick = {
                        showBottomSheet = true
                        selectedPlace = place
                        false
                    }
                ) {
                    /*Box(  sistema antigo de abrir point card, trocado por button sheet
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                    ) {
                        CreatePointCard(
                            place = place,
                            isFavorite = false
                        )
                    }*/
                }
            }
        }

        // --- UI sobreposta
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            CreateSearchBox(
                onSearchClick = { navController.navigate(Screen.Search.route) },
                filterButton = false,
            )

            Spacer(modifier = Modifier.height(630.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    onClick = {
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(moscavide, 17f)
                    },
                    modifier = Modifier.size(50.dp),
                    containerColor = AquaGreen,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = "Center",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            //CreateNavBarPage()

            // sistema button sheet
            if (showBottomSheet && selectedPlace != null) {
                LaunchedEffect(Unit) { sheetState.expand() }

                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState
                ) {
                    AquaPointSheetContent(
                        place = selectedPlace,
                        onClose = {
                            scope.launch {
                                sheetState.hide()
                                showBottomSheet = false
                                selectedPlace = null
                            }
                        },
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun AquaPointSheetContent(onClose: () -> Unit, place: AquaPoint?, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(8.dp)
    ) {
        CreatePointDetailButtonSheet(place, null, navController = navController)
    }
}

fun GetAquaPointDistance(place: AquaPoint): String {
    val myPos = LatLng(38.78166399699406, -9.102570032326907)
    val markerPosicao = LatLng(place.latitude, place.longitude)

    // Calcular distância
    val results = FloatArray(1)
    Location.distanceBetween(
        myPos.latitude,
        myPos.longitude,
        markerPosicao.latitude,
        markerPosicao.longitude,
        results
    )

    val distanceKM = results[0] / 1000.0
    val speedKMH = 8.0 // velocidade média a pé
    val timeMin = (distanceKM / speedKMH) * 60

    return if (timeMin < 1.0) {
        "%.0f sec - %.2f km".format(timeMin * 60, distanceKM)
    } else {
        "%.1f min - %.2f km".format(timeMin, distanceKM)
    }
}