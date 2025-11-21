package pt.iade.ei.aquapoint.ui.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.aquapoint.R
import pt.iade.ei.aquapoint.ui.theme.RobotoFont
import pt.iade.ei.aquapoint.ui.theme.AquaGreen
import androidx.compose.ui.res.stringResource
import pt.iade.ei.aquapoint.ui.theme.ComfortaaFont
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import pt.iade.ei.aquapoint.Screen
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
        Spacer(modifier = Modifier.height(50.dp))

        val context = LocalContext.current
        val logoutSuccessTxt = stringResource(R.string.logout_success)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
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

        Image(
            painter = painterResource(R.drawable.user_image),
            contentDescription = "Logo",
            modifier = Modifier
                .height(140.dp)
                .width(150.dp)
        )

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

        Button(
            onClick = {

                val cachedPassword = UserDataRepository.getUserPassword() ?: ""
                val user = UserDataRepository.getUserData()
                val userId = user?.id
                val currentEmail = user?.email ?: ""
                val currentJoined = user?.joined ?: ""


                val nameChanged = name != (user?.name ?: "")
                val passwordChanged = newPassword.isNotEmpty()

                if (userId == null) {
                    Toast.makeText(context, "Usuário não encontrado!", Toast.LENGTH_LONG).show()
                    // Só valida a senha se o usuário digitou uma nova senha
                } else if (passwordChanged && currentPassword != cachedPassword) {
                    Toast.makeText(context, "Senha atual incorreta!", Toast.LENGTH_LONG).show()
                } else if (!nameChanged && !passwordChanged) {
                    Toast.makeText(context, "Nenhuma alteração efetuada!", Toast.LENGTH_LONG).show()
                } else {
                    // Define qual senha será enviada para o backend
                    val passwordToUpdate = if (passwordChanged) newPassword else cachedPassword

                    // Chama a função de atualização
                    UpdateUserReturnData(
                        id = userId,
                        updatedName = name,
                        updatedPassword = passwordToUpdate,
                        email = currentEmail,
                        joined = currentJoined
                    ) { success ->
                        if (success) {
                            user.let {
                                UserDataRepository.setUserLogin(
                                    it.copy(
                                        name = name,
                                        password = passwordToUpdate
                                    )
                                )

                                currentPassword = ""
                                newPassword = ""
                            }


                            val message = when {
                                nameChanged && passwordChanged -> "Nome e senha atualizados!"
                                nameChanged -> "Nome atualizado!"
                                passwordChanged -> "Senha alterada!"
                                else -> "Nenhuma alteração efetuada!"
                            }

                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Falha ao atualizar os dados.", Toast.LENGTH_LONG).show()
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

fun UpdateUserReturnData(
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
        currentPassword = updatedPassword,
        newPassword = updatedPassword,
        joined = joined
    ) { response ->
        val success = !response.contains("Erro", ignoreCase = true)
        onResult(success)
    }
}