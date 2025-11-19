package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.classes.UserReviews
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseUserReviews
import pt.iade.ei.aquapoint.ui.pages.GetAquaPointDistance
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePointCard(place: AquaPoint, isFavorite: Boolean, onClick: (AquaPoint) -> Unit, modifier: Modifier = Modifier) {
    var reviews by remember { mutableStateOf<List<UserReviews>>(emptyList()) }

    LaunchedEffect(Unit) {
        NetworkService.getAquaPointReviews(place?.id) { result ->
            reviews = parseUserReviews(result)
        }
    }

    var finalText = place!!.name
    var opacity: Float = 1.0f

    if (place?.state_id == 2) {
        finalText = "${place!!.name} ⚠️"
        opacity = 0.5f
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .alpha(opacity)
            .padding(vertical = 13.dp),
        shape = RoundedCornerShape(40.dp),
        onClick = { onClick(place) },
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

                var isLoading by remember { mutableStateOf(true) }

                if (isLoading) {
                    CircularProgressIndicator()
                }

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("http://10.0.2.2:8080/images/aquaPoints/${place.id}.jpg")
                        .crossfade(true)
                        .error(R.drawable.no_image)           // aparece se a imagem falhar
                        .fallback(R.drawable.no_image)        // aparece se a URL for nula
                        .listener(
                            onSuccess = { _, _ -> isLoading = false },
                            onError = { _, _ -> isLoading = false }
                        )
                        .build(),
                    contentDescription = "Imagem do local",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier
                    .width(25.dp)

                )

                Text(text = finalText, fontSize = 20.sp, modifier = Modifier
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
                Text(text = "${stringResource(R.string.distance)}: ${GetAquaPointDistance(place)}",
                    fontSize = 12.sp,
                    fontFamily = ComfortaaFont)

                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${stringResource(R.string.evaluation)}: ${getAVGRating(reviews)}",
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