package pt.iade.ei.aquapoint.ui.components

import CreateNavBarPage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme


@Preview(showBackground = true )
@Composable
fun CreateSearchPage(){
    AquaPointTheme {
        val places = AquaPointsRepository.getCached() ?: emptyList()

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            var searchText by remember { mutableStateOf("") }

            CreateSearchBox(
                onSearchClick = {}, // não navega daqui
                enabled = true // pode digitar
            )

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
            //CreateNavBarPage()
        }
    }
}

