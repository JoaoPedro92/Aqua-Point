package pt.iade.ei.aquapoint.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.ui.classes.UserReviews
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.data.UserDataRepository
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService.parseUserReviews
import pt.iade.ei.aquapoint.ui.pages.GetAquaPointDistance
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont
import pt.iade.ei.aquapoint.ui.theme.StarYellow
import pt.iade.ei.aquapoint.ui.theme.DarkRed
import pt.iade.ei.aquapoint.ui.theme.DarkGreen

@Composable
fun CreatePointDetailButtonSheet(place: AquaPoint?, id: Int?, navController: NavHostController) {
    var reviews by remember { mutableStateOf<List<UserReviews>>(emptyList()) }
    var context = LocalContext.current

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
            var isLoading by remember { mutableStateOf(true) }

            if (isLoading) {
                CircularProgressIndicator()
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("http://10.0.2.2:8080/images/aquaPoints/${currentPlace?.id}.jpg")
                    .crossfade(true)
                    .error(R.drawable.no_image)           // aparece se a imagem falhar
                    .fallback(R.drawable.no_image)        // aparece se a URL for nula
                    .listener(
                        onSuccess = { _, _ -> isLoading = false },
                        onError = { _, _ -> isLoading = false }
                    )
                    .build(),
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
                var finalText = "${currentPlace!!.name}"

                if (currentPlace?.state_id == 2) {
                    finalText = "${currentPlace!!.name} ⚠️"
                }

                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp
                        )) {
                    Text(
                        text = finalText,
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

                        var favColor by remember { mutableStateOf(Color.Gray) }

                        if (AquaPointsRepository.isFavorite(currentPlace?.id)) {
                            favColor = Color.Red
                        }

                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "return",
                            tint = favColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .offset(y = -35.dp, x = 5.dp)
                                .clickable {
                                    if (UserDataRepository.getUserId() != null) {
                                        if (AquaPointsRepository.isFavorite(currentPlace?.id)) {
                                            NetworkService.removeAquaPointFromFavorites(UserDataRepository.getUserId(), currentPlace?.id) {
                                                AquaPointsRepository.updateFavoriteAquaPoints(UserDataRepository.getUserId()) { pointsData ->
                                                    AquaPointsRepository.setFavoriteCache(pointsData)

                                                    favColor = Color.Gray
                                                }
                                            }
                                        } else {
                                            NetworkService.addAquaPointToFavorite(UserDataRepository.getUserId(), currentPlace?.id) {
                                                AquaPointsRepository.updateFavoriteAquaPoints(UserDataRepository.getUserId()) { pointsData ->
                                                    AquaPointsRepository.setFavoriteCache(pointsData)

                                                    favColor = Color.Red
                                                }
                                            }
                                        }
                                    } else {
                                        navController.navigate(Screen.MainPage.route)
                                    }
                                }
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

                    var flagColor by remember { mutableStateOf(DarkRed) }
                    var aqua_point_state_modified = R.string.aqua_point_state_modified

                    if (place?.state_id == 2) {
                        flagColor = DarkGreen
                    }

                    Icon(
                        imageVector = Icons.Outlined.Flag,
                        contentDescription = "return",
                        tint = flagColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .offset(y = -15.dp, x = 5.dp)
                            .clickable {
                                if (UserDataRepository.getUserId() != null) {
                                    var newState = 1

                                    if (place?.state_id == 1) {
                                        newState = 2
                                    }

                                    NetworkService.updatePointState(place?.id, newState) { response ->
                                        AquaPointsRepository.updateAquaPoints() { pointsData ->
                                            AquaPointsRepository.setCache(pointsData)

                                            val toast = Toast.makeText(context, aqua_point_state_modified, Toast.LENGTH_LONG)
                                            toast.show()

                                            if (newState == 1) {
                                                flagColor = DarkRed
                                            } else {
                                                flagColor = DarkGreen
                                            }

                                            navController.navigate(Screen.Home.route)
                                        }
                                    }
                                } else {
                                    navController.navigate(Screen.MainPage.route)
                                }
                            }
                    )


                    Text(
                        text = stringResource(id = R.string.experience_text),
                        fontSize = 14.sp,
                        fontFamily = ComfortaaFont,
                        color = Color.Gray,
                        modifier = Modifier
                            .offset(y = -10.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    var rating by remember { mutableStateOf(0) }
                    var comment by remember { mutableStateOf("") }
                    var successMessage = stringResource(R.string.new_review_success)
                    var fillAllFieldsMessage = stringResource(R.string.fill_all_fields)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(5) { i ->
                            val starNumber = i + 1
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Estrela $starNumber",

                                tint = if (starNumber <= rating) StarYellow else Color.Black,

                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        rating = starNumber
                                    }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                        ) {
                            TextField(
                                value = comment,
                                onValueChange = { comment = it },
                                placeholder = { Text(stringResource(id = R.string.comment_placeholder)) },
                                modifier = Modifier
                                    .width(310.dp)
                                    .offset(x = -5.dp)
                                    .offset(y = 5.dp)
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

                        Column(
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Send,
                                contentDescription = "Send review",

                                tint = AquaGreen,

                                modifier = Modifier
                                    .size(23.dp)
                                    .offset(y = 21.dp)
                                    .offset(x = -1.dp)
                                    .clickable {
                                        if (UserDataRepository.getUserId() != null) {
                                            if (rating > 0 && comment != "" && comment != " ") {
                                                NetworkService.createNewReview(
                                                    UserDataRepository.getUserId(),
                                                    currentPlace?.id,
                                                    rating,
                                                    comment
                                                ) { result ->
                                                    AquaPointsRepository.updateAquaPoints { result ->
                                                        AquaPointsRepository.updateFavoriteAquaPoints(UserDataRepository.getUserId()) { result ->
                                                            NetworkService.getAquaPointReviews(currentPlace?.id) { result ->
                                                                reviews = parseUserReviews(result)

                                                                rating = 0;
                                                                comment = ""

                                                                val toast = Toast.makeText(context, successMessage, Toast.LENGTH_LONG)
                                                                toast.show()
                                                            }
                                                        };
                                                    }
                                                }
                                            } else {
                                                val toast = Toast.makeText(context, fillAllFieldsMessage, Toast.LENGTH_LONG)
                                                toast.show()
                                            }
                                        } else {
                                            navController.navigate(Screen.MainPage.route)
                                        }
                                    }
                            )
                        }
                    }
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
                                var isLoading by remember { mutableStateOf(true) }

                                if (isLoading) {
                                    CircularProgressIndicator()
                                }

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("http://10.0.2.2:8080/images/userProfiles/${review.user_id}.jpg")
                                        .crossfade(true)
                                        .memoryCachePolicy(CachePolicy.DISABLED)
                                        .diskCachePolicy(CachePolicy.DISABLED)
                                        .error(R.drawable.user_image)           // aparece se a imagem falhar
                                        .fallback(R.drawable.user_image)        // aparece se a URL for nula
                                        .listener(
                                            onSuccess = { _, _ -> isLoading = false },
                                            onError = { _, _ -> isLoading = false }
                                        )
                                        .build(),
                                    contentDescription = "Imagem de topo",
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