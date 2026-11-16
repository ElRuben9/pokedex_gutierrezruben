package gutierrez.ruben.mipokedexcompose.view.screens

import android.view.Menu
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import gutierrez.ruben.mipokedexcompose.viewModel.UserViewModel

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    onNavigateToMenu: () -> Unit,
    viewModel: UserViewModel = viewModel()
) {
    var user by remember { mutableStateOf("") }
    var password = remember { TextFieldState() }

    if (viewModel.isLoggedIn()) {
        onNavigateToMenu()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier.padding(bottom = 10.dp),
                    value = user,
                    label = {Text("Correo Electronico")},
                    onValueChange = {it -> user = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

                SecureTextField(state = password,
                    label = {Text("Contraseña")},
                    textObfuscationMode = TextObfuscationMode.RevealLastTyped,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                    viewModel.login(user, password.text as String)
                    if (!viewModel.loginError) {
                        onNavigateToMenu()
                    }
                }) {
                    Text("Iniciar Sesión")
                }
            }

            Spacer(Modifier.size(40.dp))

            if(viewModel.loginError) {
                Text(viewModel.errorDescription, color = Color.Red, fontStyle = FontStyle.Italic)
            }
        }
    }


}