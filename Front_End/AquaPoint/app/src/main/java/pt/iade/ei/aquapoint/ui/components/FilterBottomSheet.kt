package pt.iade.ei.aquapoint.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pt.iade.ei.aquapoint.ui.theme.AquaGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    selectedRating: Int,
    onRatingSelected: (Int) -> Unit,
    orderLess: Boolean,
    orderMore: Boolean,
    onOrderLess: () -> Unit,
    onOrderMore: () -> Unit,
    onApply: () -> Unit,
    onClear: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(top = 10.dp, bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Filtros", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))


            Text("Filtrar por avaliações")
            Spacer(modifier = Modifier.height(6.dp))

            Row(horizontalArrangement = Arrangement.Center) {
                (1..5).forEach { star ->
                    IconButton(onClick = { onRatingSelected(star) }) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star $star",
                            tint = if (star <= selectedRating) Color(0xFFFFD700) else Color.Gray,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))


            Text("Filtrar por opiniões")
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Menos opiniões",
                    color = if (orderLess) Color.Black else Color.Gray,
                    modifier = Modifier.clickable { onOrderLess() }
                )

                Text(
                    text = "Mais opiniões",
                    color = if (orderMore) Color.Black else Color.Gray,
                    modifier = Modifier.clickable { onOrderMore() }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                OutlinedButton(
                    onClick = onClear,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpar", color = AquaGreen)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = onApply,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AquaGreen,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Aplicar")
                }
            }
        }
    }
}
