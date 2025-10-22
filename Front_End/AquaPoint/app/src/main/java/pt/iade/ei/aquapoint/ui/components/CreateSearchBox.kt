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
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.IconButton

@Composable
fun CreateSearchBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    stringResource(R.string.search),
                    modifier = Modifier
                        .offset(y = -2.dp)
                ) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, // usa seu próprio drawable
                    contentDescription = "Pesquisar",
                    modifier = Modifier.size(22.dp)
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(16.dp))

        FloatingActionButton(
            onClick = {},
            modifier = Modifier.size(50.dp),
            containerColor = AquaGreen,
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filtro),
                contentDescription = "Filtro",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Preview(showBackground = true )
@Composable
fun PreviewSearchBox(){
    AquaPointTheme {
        CreateSearchBox()
    }
}
