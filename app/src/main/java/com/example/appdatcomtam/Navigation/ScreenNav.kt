package com.example.appdatcomtam.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appdatcomtam.Home.HomeScreen
import com.example.appdatcomtam.Quanly.QuanLyMonAnScreen
import com.example.appdatcomtam.Quanly.Them.ThemMonAnScreen
import com.example.appdatcomtam.Quanly.Them.ThemMonAnViewModel

@Composable
fun ScreenNav() {
    val viewModelThemMonAn: ThemMonAnViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.MyBottombar.route,
    ) {
        composable(Screen.MyBottombar.route) { MyBottombar() }
//        composable(Screen.Home.route) { HomeScreen() }
//        composable(Screen.Login.route) { HomeScreen() }
//        composable(Screen.SignUp.route) { HomeScreen() }
//        composable(Screen.Profile.route) { HomeScreen() }
//        composable(Screen.Thongke.route) { HomeScreen() }
//        composable(Screen.ThemMonAn.route) { ThemMonAnScreen(viewModel = viewModelThemMonAn) }
//        composable(Screen.Manager.route) { QuanLyMonAnScreen(navController) }
    }

}