package pt.iade.ei.aquapoint.ui.components

import android.graphics.fonts.FontFamily
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.HistoricalChange

@Composable
fun CreateSearchBox(
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {},
    onSearchClick: (() -> Unit)? = null,
    enabled: Boolean = false,
    filterButton: Boolean

) {
    Spacer(modifier = Modifier.height(35.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = if (enabled) searchText else "",
            onValueChange = { if (enabled) onSearchTextChange(it) },
            placeholder = {
                Text(
                    stringResource(R.string.search),
                    modifier = Modifier
                        .offset(y = 0.dp),
                    color = Color.DarkGray,
                ) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, // usa seu próprio drawable
                    contentDescription = "Pesquisar",
                    modifier = Modifier.size(22.dp),
                    tint = Color.DarkGray
                )
            },
            modifier = Modifier
                .weight(1f)
            .clickable {  if (!enabled) onSearchClick?.invoke()},
            shape = RoundedCornerShape(22.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                disabledTextColor = Color.Black
            ),
            singleLine = true,
            enabled = enabled
        )

        if (filterButton) {
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
}

@Preview(showBackground = true )
@Composable
fun PreviewSearchBox(){
    AquaPointTheme {
        //CreateSearchBox()
    }
}
