package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text

@Composable
fun CreateSearchBox(
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {},
    onSearchClick: (() -> Unit)? = null,
    enabled: Boolean = false,
    filterButton: Boolean,
    onFilterClick: () -> Unit = {},
    filterActive: Boolean = false
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
                    "Pesquisar",
                    modifier = Modifier.offset(y = 0.dp),
                    color = Color.DarkGray,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Pesquisar",
                    modifier = Modifier.size(22.dp),
                    tint = Color.DarkGray
                )
            },
            modifier = Modifier
                .weight(1f)
                .clickable { if (!enabled) onSearchClick?.invoke() },
            shape = androidx.compose.foundation.shape.RoundedCornerShape(22.dp),
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

            Box {
                FloatingActionButton(
                    onClick = onFilterClick,
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
                if (filterActive) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )
                }
            }
        }
    }
}
