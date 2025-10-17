package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont

@Composable
fun CreatePointDetail() {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        // Box com imagem e botão de voltar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bebedouro),
                contentDescription = "Imagem de topo",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // posto
        Column {

            Card(
                shape = RoundedCornerShape(40.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp
                )) {
                    Text(
                        text = stringResource(R.string.post_name),
                        fontSize = 24.sp,
                        fontFamily = ComfortaaFont,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Classificação",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "4.5")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "•")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.opinion),
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.post_description),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(id = R.string.post_distance),
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
                shape = RoundedCornerShape(40.dp),
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
                        color = Color.Black
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
                                    .size(38.dp)

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


            //avalição feita
        Card(
            shape = RoundedCornerShape(40.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp, horizontal = 20.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.bebedouro),
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.personal_name),
                        fontSize = 14.sp,
                        fontFamily = ComfortaaFont,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
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
                        text = stringResource(id = R.string.evaluation_date),
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

                Image(
                    painter = painterResource(id = R.drawable.bebedouro),
                    contentDescription = "Imagem comentário",
                    modifier = Modifier
                        .width(width = 80.dp)
                        .height(80.dp),

                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(width = 10.dp))

                    Column (
                        modifier = Modifier
                            .weight(1f)
                    ){
                        Text(
                            text = stringResource(id = R.string.user_comment),
                            fontSize = 12.sp,
                            fontFamily = ComfortaaFont,
                            lineHeight = 20.sp,
                            color = Color.Black,
                            maxLines = 3,
                        )

                        Spacer(modifier = Modifier.height(1.dp))


                        Text(
                            text = stringResource(id = R.string.continuation),
                            fontFamily = ComfortaaFont,
                            fontSize = 12.sp,
                            color = Color(0xFF4A90E2)
                        )
                    }



             }
            }

        }

            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                CreateNavBarPage()
            }

    }


}







@Preview(showBackground = true)
@Composable
fun PreviewPointDetail() {
    AquaPointTheme {
        CreatePointDetail()

    }
}
