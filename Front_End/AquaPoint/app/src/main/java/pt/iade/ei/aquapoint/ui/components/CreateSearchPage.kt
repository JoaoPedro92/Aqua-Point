package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme


@Preview(showBackground = true )
@Composable
fun PreviewSearchPage(){
    AquaPointTheme {
        val places = listOf(
            Place("Posto 1 - Santa Maria", "15min -3.8km", 4, R.drawable.aqua_point_logo, latitude = 38.78049309176663, longitude = -9.102677320690447),
            Place("Posto 2 - Moscavide", "25min - 5km", 3, R.drawable.aqua_point_logo, latitude = 38.78105763775865, longitude = -9.103412245980696),
            Place("Posto 3 - Santos", "35min - 7km", 3, R.drawable.aqua_point_logo, latitude = 38.77977799376074, longitude = -9.103589271780537),
            Place("Posto 4 - Lumiar", " 10min - 2km", 3, R.drawable.aqua_point_logo, latitude = 38.78053909197767, longitude =  -9.101379131491612)
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
        ) {
            CreateSearchBox()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            )
            {
                items(places) { place ->
                    CreatePointCard(place, false)
                }
            }
            CreateNavBarPage()
        }
    }
}

