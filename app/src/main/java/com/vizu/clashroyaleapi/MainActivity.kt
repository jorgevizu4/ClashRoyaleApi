package com.vizu.clashroyaleapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.vizu.clashroyaleapi.data.database.RepositorioAutenticacionFirebase
import com.vizu.clashroyaleapi.presentation.viewmodel.ClashRoyaleViewModel
import com.vizu.clashroyaleapi.ui.screens.ArenasListScreen
import com.vizu.clashroyaleapi.ui.screens.CardListScreen
import com.vizu.clashroyaleapi.ui.screens.LoginScreen
import com.vizu.clashroyaleapi.ui.screens.PrincipalScreen
import com.vizu.clashroyaleapi.ui.theme.ClashRoyaleApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            ClashRoyaleApiTheme {
                Surface {
                    NavegacionAplicacion(RepositorioAutenticacionFirebase())
                }
            }
        }
    }
}

@Composable
fun NavegacionAplicacion(repositorio: RepositorioAutenticacionFirebase) {
    val navController = rememberNavController()
    val vm : ClashRoyaleViewModel = viewModel()

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(repositorio, navController) }
        composable("inicio") { PrincipalScreen(repositorio, navController) }
        composable("cards") { CardListScreen(vm, repositorio, navController) }
        composable("arenas") { ArenasListScreen(vm, repositorio, navController) }
    }
}