package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont


@Preview(showBackground = true )
@Composable
fun PreviewSearchPage(){
    AquaPointTheme {
        val places = listOf(
            Place("Posto 1 - Santa Maria", "15min -3.8km", 4, R.drawable.aqua_point_logo),
            Place("Posto 2 - Moscavide", "25min - 5km", 3, R.drawable.aqua_point_logo),
            Place("Posto 3 - Santos", "35min - 7km", 3, R.drawable.aqua_point_logo),
            Place("Posto 4 - Lumiar", " 10min - 2km", 3, R.drawable.aqua_point_logo)
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

