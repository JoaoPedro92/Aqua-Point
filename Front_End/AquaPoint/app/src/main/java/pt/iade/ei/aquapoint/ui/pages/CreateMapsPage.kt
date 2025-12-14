package pt.iade.ei.aquapoint.ui.pages

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
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
    val activity = LocalContext.current as Activity
    val context = LocalContext.current

    var hasLocationPermission by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            hasLocationPermission = true
        } else {
            activity.finish()
        }
    }

    // codigo para pedir permissões quando o screen for criado
    LaunchedEffect(Unit) {
        val granted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (granted) {
            hasLocationPermission = true
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    var cameraPositionState = rememberCameraPositionState()
    if (hasLocationPermission) {
        // descorir coordenadas do utilizador
        val fusedLocationClient = remember {
            LocationServices.getFusedLocationProviderClient(context)
        }

        var foundLocalization by remember { mutableStateOf<Boolean>(false) }

        LaunchedEffect(Unit) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latLng = LatLng(location.latitude, location.longitude)

                    foundLocalization = true

                    UserCoordinates.setLatitude(latLng.latitude)
                    UserCoordinates.setLongitude(latLng.longitude)

                    UserCoordinates.setStartLatitude(latLng.latitude)
                    UserCoordinates.setStartLongitude(latLng.longitude)
                }
            }
        }

        // ao obter coordenadas, atualizar a camera do mapa
        if (foundLocalization) {
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(UserCoordinates.getLatitude(), UserCoordinates.getLongitude()), 16.5f)
            }
        }
    } else {
        // se não tiver permissoes, fica com coordenadas default

        val moscavide = LatLng(UserCoordinates.getLatitude(), UserCoordinates.getLongitude())

        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(moscavide, 16.5f)
        }
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

                LaunchedEffect(clickedLatLng) { // dar zoom ao clicar aleatoriamente no mapa
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newLatLngZoom(pos, 16.5f)
                    )
                }
            }
        }

        // --- UI sobreposta
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                CreateSearchBox(
                    onSearchClick = { navController.navigate(Screen.Search.route) },
                    filterButton = false,
                )
            }

            var resetMapView by remember { mutableStateOf(false) }

            FloatingActionButton(
                onClick = {
                    resetMapView = true
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 20.dp, vertical = 100.dp),
                containerColor = AquaGreen,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = "Center",
                    modifier = Modifier.size(28.dp)
                )
            }

            LaunchedEffect(resetMapView) { // dar zoom ao clicar aleatoriamente no mapa
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(LatLng(UserCoordinates.getLatitude(), UserCoordinates.getLongitude()), 16.5f)
                )

                resetMapView = false
            }

            if (showBottomSheet && selectedPlace != null) {
                LaunchedEffect(Unit) {
                    sheetState.expand()
                }

                LaunchedEffect(selectedPlace) {
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newLatLngZoom(LatLng(selectedPlace!!.latitude, selectedPlace!!.longitude), 16.5f)
                    )
                }

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
            .fillMaxHeight(0.75f)
            .padding(8.dp)
    ) {
        CreatePointDetailButtonSheet(place, null, navController = navController, onClose)
    }
}

fun GetAquaPointDistance(place: AquaPoint): String {
    val myPos = LatLng(UserCoordinates.getLatitude(), UserCoordinates.getLongitude())
    val markerPosicao = LatLng(place.latitude, place.longitude)
    var finalString = ""

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

    if (timeMin < 1.0) {
        finalString = "%.0f sec - %.2f km".format(timeMin * 60, distanceKM)
    } else if (timeMin > 59) {
        finalString = "%.1f h - %.2f km".format(timeMin/60, distanceKM)
    } else {
        finalString = "%.1f min - %.2f km".format(timeMin, distanceKM)
    }

    return finalString
}