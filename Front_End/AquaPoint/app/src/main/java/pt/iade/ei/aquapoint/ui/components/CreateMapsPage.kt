package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import pt.iade.ei.aquapoint.ui.theme.AquaGreen

@Composable
fun MapScreen(places: List<Place>) {
    val moscavide = LatLng(38.78166399699406, -9.102570032326907)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(moscavide, 17f)
    }

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

                MarkerInfoWindow(
                    state = markerState,
                    title = place.name,
                    snippet = place.distance,
                    onClick = {
                        false
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                    ) {
                        CreatePointCard(
                            place = place,
                            isFavorite = false
                        )
                    }
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

            CreateSearchBox()

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

            CreateNavBarPage()
        }
    }
}
