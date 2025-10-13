package pt.iade.ei.aquapoint.ui.components

import android.graphics.fonts.FontFamily
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme
import pt.iade.ei.aquapoint.ui.theme.RobotoFont
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.IconButton

@Composable
fun CreateHomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(R.drawable.aqua_point_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f), // ocupa 70% da altura
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.home_page_image),
                contentDescription = "Home Page Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // ajusta a imagem
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Row (
            modifier = Modifier
        ){
            Button(
                onClick = {  },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, AquaGreen),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.5f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    fontFamily = RobotoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = AquaGreen
                )
            }

            Button(
                onClick = {  },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AquaGreen,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.95f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.register),
                    fontFamily = RobotoFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    AquaPointTheme {
        CreateHomePage()
    }
}
