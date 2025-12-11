package pt.iade.ei.aquapoint.ui.pages

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.DataSaverOff
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.data.AquaPointsRepository
import pt.iade.ei.aquapoint.data.LocalsRepository
import pt.iade.ei.aquapoint.data.UserCoordinates
import pt.iade.ei.aquapoint.data.UserDataRepository
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService

@Composable
fun CreateAddAquaPointPage(navController: NavHostController) {
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
                tint = Color.Gray,
                modifier = Modifier
                    .clickable{
                        navController.navigate(Screen.Home.route)
                    }
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            selectedImageUri = uri
        }

        var finalImage = painterResource(R.drawable.add_new_point)

        if (selectedImageUri != null) {
            finalImage = rememberAsyncImagePainter(selectedImageUri)
        }


        Image(
            painter = finalImage,
            contentDescription = "Logo",
            modifier = Modifier
                .height(150.dp)
                .width(200.dp)
                .clickable {
                    galleryLauncher.launch("image/*")
                }
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

        var defaultSelectsText = stringResource(R.string.choose_an_option)
        var selectedText = stringResource(R.string.select_buttons_selected)

        var selectedOption by remember { mutableStateOf<Int?>(null) }
        var expandedType by remember { mutableStateOf(false) }
        var selectButtonTypeText by remember { mutableStateOf(defaultSelectsText) }

        Box {
            Button(
                onClick = { expandedType = !expandedType },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, AquaGreen),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption != null) AquaGreen else Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = selectButtonTypeText,
                    fontFamily = RobotoFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = if (selectedOption != null) Color.White else AquaGreen
                )
            }

            DropdownMenu(
                expanded = expandedType,
                onDismissRequest = { expandedType = false },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.aqua_point_type_person)) },
                    leadingIcon = { Icon(Icons.Outlined.Person, null) },
                    onClick = {
                        selectedOption = 1
                        selectButtonTypeText = selectedText
                        expandedType = false
                    }
                )

                DropdownMenuItem(
                    text = { Text(stringResource(R.string.aqua_point_type_animal)) },
                    leadingIcon = { Icon(Icons.Outlined.Pets, null) },
                    onClick = {
                        selectedOption = 1
                        selectButtonTypeText = selectedText
                        expandedType = false
                    }
                )

                DropdownMenuItem(
                    text = { Text(stringResource(R.string.aqua_point_type_both)) },
                    leadingIcon = { Icon(Icons.Outlined.DataSaverOff, null) },
                    onClick = {
                        selectedOption = 2
                        selectButtonTypeText = selectedText
                        expandedType = false
                    }
                )
            }
        }


        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.aqua_point_choose_local),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 1.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        var selectedOptionLocal by remember { mutableStateOf<Int?>(null) }
        var expandedTypeLocal by remember { mutableStateOf(false) }
        var selectButtonLocalText by remember { mutableStateOf(defaultSelectsText) }
        var localsData = LocalsRepository.getCached()

        if (localsData == null) {
            LocalsRepository.updateLocals() { locals ->
                localsData = locals
            }
        }

        Box {
            Button(
                onClick = { expandedTypeLocal = !expandedTypeLocal },
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, AquaGreen),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOptionLocal != null) AquaGreen else Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = selectButtonLocalText,
                    fontFamily = RobotoFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = if (selectedOptionLocal != null) Color.White else AquaGreen
                )
            }

            DropdownMenu(
                expanded = expandedTypeLocal,
                onDismissRequest = { expandedTypeLocal = false },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            ) {

                localsData?.forEach { localsData ->
                    DropdownMenuItem(
                        text = { Text(localsData.local_name) },
                        leadingIcon = { Icon(Icons.Outlined.Map, null) },
                        onClick = {
                            selectedOptionLocal = localsData.id
                            selectButtonLocalText = selectedText
                            expandedTypeLocal = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        var context = LocalContext.current
        val fill_all_fields = stringResource(R.string.fill_all_fields)
        val point_already_exists = stringResource(R.string.point_already_exists)
        val aqua_point_added_with_success = stringResource(R.string.aqua_point_added_with_success)

        Button(
            onClick = {
                if (aquaPointName != "" && aquaPointName != " " && selectedImageUri != null && selectedOptionLocal != null && selectedOption != null) {
                    if (AquaPointsRepository.doesPointExistsByName(aquaPointName)) {
                        val toast = Toast.makeText(context, point_already_exists, Toast.LENGTH_LONG)
                        toast.show()
                    } else {
                        val inputStream = context.contentResolver.openInputStream(selectedImageUri!!)
                        val bytes = inputStream?.readBytes()

                        NetworkService.createNewAquaPoint(
                            aquaPointName,
                            selectedOption,
                            UserCoordinates.getLatitude(),
                            UserCoordinates.getLongitude(),
                            selectedOptionLocal
                        ) { responseString ->
                            val newAquaPoint = NetworkService.parseAquaPoint(responseString)

                            aquaPointName = ""
                            selectedOption = null
                            selectedImageUri = null
                            selectedOptionLocal = null
                            UserCoordinates.restoreDefaults()

                            val toast = Toast.makeText(context, aqua_point_added_with_success, Toast.LENGTH_LONG)
                            toast.show()

                            if (bytes != null) {
                                NetworkService.uploadNewImage(bytes, "${newAquaPoint.id}.jpg") { response ->
                                    UpdateAquaPointsData(navController)
                                }
                            } else {
                                UpdateAquaPointsData(navController)
                            }
                        }
                    }
                } else {
                    val toast = Toast.makeText(context, fill_all_fields, Toast.LENGTH_LONG)
                    toast.show()
                }
            },
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

fun UpdateAquaPointsData(navController: NavHostController) {
    AquaPointsRepository.updateFavoriteAquaPoints(UserDataRepository.getUserId()) { pointsData ->
        AquaPointsRepository.setFavoriteCache(pointsData)

        AquaPointsRepository.updateAquaPoints() { pointsData ->
            AquaPointsRepository.setCache(pointsData)

            navController.navigate(Screen.Home.route)
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewAddAquaPointPage() {
    AquaPointTheme {
        CreateAddAquaPointPage()
    }
}*/