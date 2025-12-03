package pt.iade.ei.aquapoint.ui.pages

import android.location.Location
import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.data.UserCoordinates
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseAquaPoints
import pt.iade.ei.aquapoint.ui.components.CreatePointDetailButtonSheet
import pt.iade.ei.aquapoint.ui.components.CreateSearchBox
import pt.iade.ei.aquapoint.ui.theme.AquaGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavHostController) {
    val moscavide = LatLng(UserCoordinates.getLatitude(), UserCoordinates.getLongitude())
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(moscavide, 17f)
    }

    // Estado do BottomSheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedPlace by remember { mutableStateOf<AquaPoint?>(null) }

    var clickedLatLng by remember { mutableStateOf<LatLng?>(null) }

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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // --- Mapa ---
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                clickedLatLng = latLng
            }
        ) {
            for (place in places) {
                val markerState = rememberMarkerState(
                    position = LatLng(place.latitude, place.longitude)
                )

                var markerStyle: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(200f)
                var isFavorite = AquaPointsRepository.isFavorite(place.id)

                if (isFavorite) {
                    if (place.state_id == 2) {
                        markerStyle = BitmapDescriptorFactory.fromResource(R.drawable.favorite_not_working)
                    } else {
                        markerStyle = BitmapDescriptorFactory.fromResource(R.drawable.favorite_working)
                    }
                } else if (place.state_id == 2) {
                    markerStyle = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                }

                MarkerInfoWindow(
                    state = markerState,
                    title = place.name,
                    /*snippet = place.distance,*/
                    icon = markerStyle,
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

            clickedLatLng?.let { pos ->
                MarkerInfoWindow(
                    state = MarkerState(position = pos),
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.add_new_aqua_point_marker),
                    title = stringResource(R.string.add_new_point),
                    onClick = {
                        UserCoordinates.setLatitude(pos.latitude)
                        UserCoordinates.setLongitude(pos.longitude)

                        navController.navigate(Screen.Add.route)

                        true
                    }
                )
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