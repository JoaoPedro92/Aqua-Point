package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont

data class Place(
    val name: String,
    val distance: String,
    val rating: Int,
    val imageRes: Int
)

@Composable
fun CreatePointCard(place: Place, isFavorite: Boolean, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        shape = RoundedCornerShape(40.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (isFavorite) {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorito",
                        tint = Color.Red,
                        modifier = Modifier
                            .offset(x= -5.dp)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = place.imageRes),
                    contentDescription = "Imagem do local",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier
                    .width(25.dp)

                )

                Text(text = place.name, fontSize = 20.sp, modifier = Modifier
                    .offset(y = 6.dp))
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
                Text(text = "${stringResource(R.string.distance)}: ${place.distance}",
                    fontSize = 12.sp,
                    fontFamily = ComfortaaFont)

                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${stringResource(R.string.evaluation)}: ${place.rating}",
                        fontSize = 12.sp,
                        fontFamily = ComfortaaFont)

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