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
fun CreateLoginPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "return",
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.login_low),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(70.dp))

        OutlinedTextField(
            value = "",
            shape = RoundedCornerShape(16.dp),
            onValueChange = {  },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AquaGreen,
                unfocusedBorderColor = AquaGreen,
                focusedLabelColor = AquaGreen,
                unfocusedLabelColor = AquaGreen,
            ),
            label = { Text(stringResource(R.string.mail_example)) },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = "",
            shape = RoundedCornerShape(16.dp),
            onValueChange = {  },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AquaGreen,
                unfocusedBorderColor = AquaGreen,
                focusedLabelColor = AquaGreen,
                unfocusedLabelColor = AquaGreen,
            ),
            label = { Text(stringResource(R.string.password_input)) },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 8.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = {  },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AquaGreen
            ),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.log_in),
                fontFamily = RobotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() {
    AquaPointTheme {
        CreateLoginPage()
    }
}
