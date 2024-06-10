package com.example.appdatcomtam.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appdatcomtam.Home.HomeScreen
import com.example.appdatcomtam.Login.LoginScreen
import com.example.appdatcomtam.Quanly.QuanLyMonAnScreen
import com.example.appdatcomtam.Quanly.Them.ThemMonAnScreen
import com.example.appdatcomtam.Quanly.Them.ThemMonAnViewModel

@Composable
fun ScreenNav() {
    val navController = rememberNavController()
    val  context = LocalContext.current
    NavHost(
        navController = navController, startDestination = Screen.LoginScreen.route,

    ) {
        composable(Screen.MyBottombar.route) { MyBottombar(navController) }
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.LoginScreen.route) { LoginScreen(navController,context) }
    }
}
