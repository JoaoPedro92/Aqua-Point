package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSearchBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically,


            ) {
            SearchBar(
                query = "",
                onQueryChange = { },
                onSearch = { },
                active = false,
                onActiveChange = { },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search),
                        modifier = Modifier
                            .offset(y = -2.dp))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ícone de pesquisa",
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)



            ){}

            Spacer(modifier = Modifier.width(20.dp))

            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = 3.dp),

                containerColor = AquaGreen,
                contentColor = Color.White

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filtro),
                    contentDescription = "Filtro",
                    modifier = Modifier.size(38.dp)
                )
            }
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
