package com.vizu.clashroyaleapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vizu.clashroyaleapi.data.database.RepositorioAutenticacionFirebase

@Composable
fun PrincipalScreen(
    repositorio: RepositorioAutenticacionFirebase,
    navController: NavHostController
) {
    val usuario = repositorio.obtenerUsuarioActual()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido, ${usuario?.email ?: "Usuario"}",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            repositorio.cerrarSesion()
            navController.navigate("login") {
                popUpTo("inicio") { inclusive = true }
            }
        }
        ) {
            Text("Cerrar Sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("cards")
        }
        ) {
            Text("Ver Cartas")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("arenas")
        }
        ) {
            Text("Ver Arenas")
        }
    }
}