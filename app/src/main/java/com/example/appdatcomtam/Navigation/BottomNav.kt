package com.example.appdatcomtam.Navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appdatcomtam.Category.CategoryManager
import com.example.appdatcomtam.Home.HomeScreen
import com.example.appdatcomtam.Quanly.QuanLyMonAnScreen
import com.example.appdatcomtam.Quanly.Sua.DanhSachScreen
import com.example.appdatcomtam.Quanly.Sua.DanhSachViewModel
import com.example.appdatcomtam.Quanly.Sua.SuaMonAnScreen
import com.example.appdatcomtam.Quanly.Sua.SuaMonAnViewModel
import com.example.appdatcomtam.Quanly.Them.ThemMonAnScreen
import com.example.appdatcomtam.Quanly.Them.ThemMonAnViewModel
import com.example.appdatcomtam.R


@Preview(showBackground = true)
@Composable
fun MyBottombar(navCtrl: NavController? = null) {
    val navController = rememberNavController()
    val selected = remember { mutableStateOf(Screen.Home.route) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                modifier = Modifier
                    .height(100.dp)
            ) {

                // home
                IconButton(
                    onClick = {
                        selected.value = Screen.Home.route
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {

                    Image(
                        painter = painterResource(id = if (selected.value == Screen.Home.route) R.drawable.home1 else R.drawable.home2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                    )
                }

                // favorite
                IconButton(
                    onClick = {
                        selected.value = Screen.Thongke.route
                        navController.navigate(Screen.Thongke.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = if (selected.value == Screen.Thongke.route) R.drawable.thongke1 else R.drawable.thongke2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Screen.Notification.route
                        navController.navigate(Screen.Notification.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = if (selected.value == Screen.Notification.route) R.drawable.quanly1 else R.drawable.quanly2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Screen.Profile.route
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = if (selected.value == Screen.Profile.route) R.drawable.user1 else R.drawable.user2),
                        contentDescription = null,
                        modifier = Modifier
                            .size(26.dp)
                    )
                }
            }
        }
    ) {
        val viewModelThemMonAn: ThemMonAnViewModel = viewModel()
        val viewModelDanhSachMonAn: DanhSachViewModel = viewModel()
        val viewModelSuaMonAn: SuaMonAnViewModel = viewModel()
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Thongke.route) {
                CategoryManager(navCtrl)
            }
            composable(Screen.Notification.route) {
                QuanLyMonAnScreen(navController)
            }
            composable(Screen.Profile.route) {
                com.example.appdatcomtam.Home.Profile()
            }
            composable(Screen.ThemMonAn.route) { ThemMonAnScreen(viewModel = viewModelThemMonAn) }
            composable(Screen.DanhSachMonAn.route) {
                DanhSachScreen(viewModel = viewModelDanhSachMonAn, navController = navController)
            }
//            composable(Screen.SuaMonAn.route) { SuaMonAnScreen(viewModel = viewModelSuaMonAn) }

            composable("${Screen.SuaMonAn.route}/{id}/{tenMonAn}/{gia}/{hinhAnh}") {
                SuaMonAnScreen(
                    viewModel = viewModelSuaMonAn,
                    id = it.arguments?.getString("id").toString(),
                    tenMonAn = it.arguments?.getString("tenMonAn").toString(),
                    gia = it.arguments?.getString("gia").toString(),
                    hinhAnh = it.arguments?.getString("hinhAnh").toString()
                    ,navController = navController
                )
            }
        }
    }
}