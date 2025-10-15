package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme

data class Place(
    val name: String,
    val distance: String,
    val rating: Int,
    val imageRes: Int
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
             contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 65.dp),
                 verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                query = "",
                onQueryChange = { },
                onSearch = { },
                active = false,
                onActiveChange = { },
                placeholder = { Text("Procurar") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ícone de pesquisa",
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),

            ){}

            Spacer(modifier = Modifier.width(20.dp))

            FloatingActionButton(
                onClick = {},
                modifier = Modifier.size(50.dp),
                containerColor = AquaGreen,
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filtro),
                    contentDescription = "Filtro",
                    modifier = Modifier.size(38.dp)
                )
            }
        }
    }
}

@Composable
fun PointCard(place: Place, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
             shape = RoundedCornerShape(20.dp), // bordas arredondadas
            elevation = CardDefaults.cardElevation(8.dp) // sombra
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = place.imageRes),
                    contentDescription = "Imagem do local",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(50.dp))

                Text(text = place.name, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Segunda linha: distância à esquerda, avaliação à direita
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Distância: ${place.distance}", fontSize = 12.sp)

                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Avaliação: ${place.rating}", fontSize = 12.sp)

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Avaliação",
                        tint = Color(0xFFFFC107)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(places: List<Place>) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBox()

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(places) { place ->
                PointCard(place)
            }
        }
    }
}





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

        MainScreen(places)
    }
 }

