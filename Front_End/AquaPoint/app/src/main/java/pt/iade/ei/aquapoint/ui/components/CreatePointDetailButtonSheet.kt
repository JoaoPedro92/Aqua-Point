package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.AquaPoint
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.UserReviews
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseUserReviews
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont


@Composable
fun CreatePointDetailButtonSheet(place: AquaPoint?, id: Int?) {
    var reviews by remember { mutableStateOf<List<UserReviews>>(emptyList()) }

    var currentPlace: AquaPoint? = place

    if (currentPlace == null && id != null) {
        currentPlace = AquaPointsRepository.getPointById(id)
    }

    LaunchedEffect(Unit) {
        NetworkService.getAquaPointReviews(currentPlace?.id) { result ->
            reviews = parseUserReviews(result)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Box com imagem e botão de voltar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bebedouro),
                contentDescription = "Imagem de topo",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            /*Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 20.dp)
            )*/
        }

        Spacer(modifier = Modifier.height(16.dp))

        // posto
        Column {

            Card(
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp
                        )) {
                    Text(
                        text = currentPlace!!.name,
                        fontSize = 23.sp,
                        fontFamily = ComfortaaFont,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(y = 7.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Classificação",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = getAVGRating(reviews),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "•")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${reviews.size} ${stringResource(R.string.opinion)}",
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    /*Text(
                        text = stringResource(id = R.string.post_description),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )*/

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = GetAquaPointDistance(currentPlace),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Avaliação
        Column {
            Card(
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)

            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(id = R.string.evaluation_title),
                        fontFamily = ComfortaaFont,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(y = 7.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.experience_text),
                        fontSize = 14.sp,
                        fontFamily = ComfortaaFont,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Estrela de avaliação",
                                tint = Color.Black,

                                modifier = Modifier
                                    .size(30.dp)

                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(stringResource(id = R.string.comment_placeholder)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red
                        )

                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //avalição feita
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

            )
            {
                items(reviews) { review ->
                    Card(
                        shape = RoundedCornerShape(30.dp),
                        elevation = CardDefaults.cardElevation(2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .offset(y = 7.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.user_image),
                                    contentDescription = "Perfil",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = review.name,
                                    fontSize = 14.sp,
                                    fontFamily = ComfortaaFont,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))


                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(review.rating) {
                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "Estrela",
                                        tint = Color(0xFFFFD700),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = review.date,
                                    fontSize = 12.sp,
                                    fontFamily = ComfortaaFont,
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),

                                ){

                                Column (
                                    modifier = Modifier
                                        .weight(1f)
                                ){
                                    Text(
                                        text = review.comment,
                                        fontSize = 12.sp,
                                        fontFamily = ComfortaaFont,
                                        lineHeight = 20.sp,
                                        color = Color.Black,
                                        maxLines = 3,
                                    )

                                    /*Spacer(modifier = Modifier.height(1.dp))


                                    Text(
                                        text = stringResource(id = R.string.continuation),
                                        fontFamily = ComfortaaFont,
                                        fontSize = 12.sp,
                                        color = Color(0xFF4A90E2)
                                    )*/
                                }



                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }



        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            //CreateNavBarPage()
        }

    }


}