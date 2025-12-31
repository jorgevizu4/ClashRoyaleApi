package com.vizu.clashroyaleapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vizu.clashroyaleapi.data.database.RepositorioAutenticacionFirebase

@Composable
fun LoginScreen(repositorio: RepositorioAutenticacionFirebase, navController: NavHostController) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var cargando by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Clash Royale API", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    cargando = true
                    repositorio.iniciarSesion(correo, contrasena) { exito, error ->
                        cargando = false
                        if (exito) {
                            navController.navigate("inicio") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            mensajeError = error ?: "Error al iniciar sesión"
                        }
                    }
                }
            ) {
                Text("Iniciar Sesión")
            }
            Button(
                onClick = {
                    cargando = true
                    repositorio.registrar(correo, contrasena) { exito, error ->
                        cargando = false
                        if (exito) {
                            navController.navigate("inicio") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            mensajeError = error ?: "Error en el registro"
                        }
                    }
                }
            ) {
                Text("Registrarse")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (cargando) {
            CircularProgressIndicator()
        }
        mensajeError?.let { msg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }
    }
}

