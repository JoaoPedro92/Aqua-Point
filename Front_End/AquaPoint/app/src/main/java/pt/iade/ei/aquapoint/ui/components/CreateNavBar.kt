package pt.iade.ei.aquapoint.ui.components

import android.graphics.fonts.FontFamily
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.IconButton

@Composable
fun CreateNavBarPage() {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(55.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(30.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(30.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {  },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color.Transparent),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = "return",
                tint = Color.Gray,
                modifier = Modifier.size(92.dp)
            )
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color.Transparent),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "return",
                tint = Color.Gray,
                modifier = Modifier.size(92.dp)
            )
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AquaGreen
            ),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "return",
                tint = Color.White
            )
        }

        Button(
            onClick = {  },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color.Transparent),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "return",
                tint = Color.Gray,
                modifier = Modifier.size(92.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNavBarPage() {
    AquaPointTheme {
        CreateNavBarPage()
    }
}
