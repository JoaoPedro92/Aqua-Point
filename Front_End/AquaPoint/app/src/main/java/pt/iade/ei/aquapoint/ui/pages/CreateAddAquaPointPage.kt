package pt.iade.ei.aquapoint.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CreateAddAquaPointPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

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

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(R.drawable.add_new_point),
            contentDescription = "Logo",
            modifier = Modifier
                .height(150.dp)
                .width(200.dp)
        )

        Spacer(modifier = Modifier.height(55.dp))

        Text(
            text = stringResource(R.string.add_point_name),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 1.dp)
        )

        var aquaPointName by remember { mutableStateOf("") }

        OutlinedTextField(
            value = aquaPointName,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { aquaPointName = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AquaGreen,
                unfocusedBorderColor = AquaGreen,
                focusedLabelColor = AquaGreen,
                unfocusedLabelColor = AquaGreen,
            ),
            label = { "" },
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 2.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.aqua_point_type),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 1.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        var selectedOption by remember { mutableStateOf(0) }

        Button(
            onClick = { selectedOption = 0 },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, AquaGreen),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption == 0) AquaGreen else Color.White,

                ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(55.dp)
        ) {
            Text(
                text = stringResource(R.string.aqua_point_type_person),
                fontFamily = RobotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = if (selectedOption == 0) Color.White else AquaGreen
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { selectedOption = 1 },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, AquaGreen),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption == 1) AquaGreen else Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(55.dp)
        ) {
            Text(
                text = stringResource(R.string.aqua_point_type_animal),
                fontFamily = RobotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = if (selectedOption == 1) Color.White else AquaGreen
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { selectedOption = 2 },
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, AquaGreen),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption == 2) AquaGreen else Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(55.dp)
        ) {
            Text(
                text = stringResource(R.string.aqua_point_type_both),
                fontFamily = RobotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = if (selectedOption == 2) Color.White else AquaGreen
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {  },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AquaGreen
            ),
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.submit),
                fontFamily = RobotoFont,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //CreateNavBarPage()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddAquaPointPage() {
    AquaPointTheme {
        CreateAddAquaPointPage()
    }
}