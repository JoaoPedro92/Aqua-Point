package pt.iade.ei.aquapoint.ui.pages

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.RobotoFont
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import androidx.compose.ui.res.stringResource
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import org.mindrot.jbcrypt.BCrypt
import pt.iade.ei.aquapoint.Screen
import pt.iade.ei.aquapoint.data.UserCoordinates
import pt.iade.ei.aquapoint.data.UserDataRepository
import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.classes.UserData

@Composable
fun CreatePersonalArea(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(55.dp))

        val context = LocalContext.current
        val logoutSuccessTxt = stringResource(R.string.logout_success)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
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

            Icon(
                imageVector = Icons.Filled.DoorBack,
                contentDescription = "return",
                tint = Color.Gray,
                modifier = Modifier
                    .clickable {
                        if (UserDataRepository.getUserId() != null) {
                            val toast = Toast.makeText(context, logoutSuccessTxt, Toast.LENGTH_LONG)
                            toast.show()

                            UserDataRepository.removeData()
                            navController.navigate(Screen.MainPage.route)
                        }
                    }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(R.string.personal_area),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 32.dp)
        )

        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            val inputStream = context.contentResolver.openInputStream(uri!!)
            val bytes = inputStream?.readBytes()

            if (bytes != null) {
                NetworkService.uploadNewUserImage(bytes, "${UserDataRepository.getUserId()}.jpg") { response ->
                    selectedImageUri = uri
                }
            }
        }

        if (selectedImageUri == null) {
            var isLoading by remember { mutableStateOf(true) }

            if (isLoading) {
                CircularProgressIndicator()
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(NetworkService.createImageURL("images/userProfiles/${UserDataRepository.getUserId()}.jpg"))
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .error(R.drawable.user_image)           // aparece se a imagem falhar
                    .fallback(R.drawable.user_image)        // aparece se a URL for nula
                    .listener(
                        onSuccess = { _, _ -> isLoading = false },
                        onError = { _, _ -> isLoading = false }
                    )
                    .build(),
                contentDescription = "Imagem de topo",
                modifier = Modifier
                    .height(140.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        galleryLauncher.launch("image/*")
                    },
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(140.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.height(25.dp))


        var name by remember { mutableStateOf(UserDataRepository.getUserName() ?: "") }

        Text(
            text = stringResource(R.string.your_name),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 1.dp)
        )

        OutlinedTextField(
            value = name,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { name = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AquaGreen,
                unfocusedBorderColor = AquaGreen,
                focusedLabelColor = AquaGreen,
                unfocusedLabelColor = AquaGreen,
            ),
            label = { Text(stringResource(R.string.your_name)) },
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 2.dp),
            singleLine = true
        )

        var currentPassword by remember { mutableStateOf("") }
        Text(
            text = stringResource(R.string.current_password),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 1.dp)
        )

        OutlinedTextField(
            value = currentPassword,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { currentPassword = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AquaGreen,
                unfocusedBorderColor = AquaGreen,
                focusedLabelColor = AquaGreen,
                unfocusedLabelColor = AquaGreen,
            ),
            label = { Text(stringResource(R.string.password_input)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 2.dp),
            singleLine = true
        )

        var newPassword by remember { mutableStateOf("") }
        Text(
            text = stringResource(R.string.new_password_area),
            fontFamily = ComfortaaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, bottom = 1.dp)
        )
        OutlinedTextField(
            value = newPassword,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { newPassword = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AquaGreen,
                unfocusedBorderColor = AquaGreen,
                focusedLabelColor = AquaGreen,
                unfocusedLabelColor = AquaGreen,
            ),
            label = { Text(stringResource(R.string.password_input)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 2.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        val currentPasswordIncorrect = stringResource(R.string.current_password_incorrect)
        val fillFields = stringResource(R.string.fill_fields)
        val failedUpdateData = stringResource(R.string.failed_update_data)

        val both_updated = stringResource(R.string.name_password_updated)
        val name_updated = stringResource(R.string.name_updated)
        val password_updated = stringResource(R.string.password_updated)

        Button(
            onClick = {

                UserDataRepository.getUserData()?.let { user ->
                    val cachedPassword = UserDataRepository.getUserPassword() ?: ""

                    val nameChanged = name != user.name
                    val passwordChanged = newPassword.isNotEmpty()

                    when {
                        passwordChanged && !BCrypt.checkpw(currentPassword, cachedPassword) -> {
                            Toast.makeText(context, currentPasswordIncorrect, Toast.LENGTH_LONG).show()
                        }
                        !nameChanged && !passwordChanged -> {
                            Toast.makeText(context, fillFields, Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            val passwordToUpdate = if (passwordChanged) BCrypt.hashpw(newPassword, BCrypt.gensalt()) else cachedPassword

                            UpdateUserData(
                                id = user.id,
                                updatedName = name,
                                updatedPassword = passwordToUpdate,
                                email = user.email,
                                joined = user.joined
                            ) { success ->
                                if (success) {
                                    UserDataRepository.setUserLogin(
                                        user.copy(
                                            name = name,
                                            password = passwordToUpdate
                                        )
                                    )

                                    currentPassword = ""
                                    newPassword = ""

                                    val message = when {
                                        nameChanged && passwordChanged -> both_updated
                                        nameChanged -> name_updated
                                        passwordChanged -> password_updated
                                        else -> ""
                                    }
                                    if (message.isNotEmpty()) Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(context, failedUpdateData, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
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

        Spacer(modifier = Modifier.height(30.dp))

    }
}


fun UpdateUserData(
    id: Int,
    email: String,
    updatedName: String,
    updatedPassword: String,
    joined: String,
    onResult: (Boolean) -> Unit
) {
    NetworkService.updateUser(
        id = id,
        email = email,
        newName = updatedName,
        newPassword = updatedPassword,
        joined = joined
    ) { response ->
        val success = !response.contains("Erro", ignoreCase = true)
        onResult(success)
    }
}