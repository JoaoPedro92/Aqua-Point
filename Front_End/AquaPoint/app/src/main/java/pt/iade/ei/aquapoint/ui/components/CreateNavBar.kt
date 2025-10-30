import android.R.attr.height
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import pt.iade.ei.aquapoint.ui.theme.AquaPointTheme

@Composable
fun CreateNavBarPage(items: List<Screen>, navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(2) } // começa no Home, por ex.

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp,
        modifier = Modifier
            .height(60.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(45.dp)
            )
            .clip(RoundedCornerShape(45.dp)),
        windowInsets = WindowInsets(0.dp) // corrige o problema de responsividade
    ) {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(screen.route)
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(140.dp)
                            .border(
                                width = if (selectedItem == index) 2.dp else 0.dp,
                                color = if (selectedItem == index) AquaGreen else Color.Transparent,
                                shape = RoundedCornerShape(25.dp)
                            )
                            .background(
                                color = if (selectedItem == index) AquaGreen else Color.Transparent,
                                shape = RoundedCornerShape(25.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        screen.icon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = if (selectedItem == index) Color.White else Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
