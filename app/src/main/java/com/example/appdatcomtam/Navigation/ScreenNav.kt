package com.example.appdatcomtam.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appdatcomtam.Login.LoginScreen

@Composable
fun ScreenNav() {
    val navController = rememberNavController()
    val  context = LocalContext.current
    NavHost(
        navController = navController, startDestination = Screen.LoginScreen.route,

    ) {
        composable(Screen.MyBottombar.route) { MyBottombar(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController,context) }
    }
}
