package com.example.appdatcomtam.Home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Preview(showBackground = true)
@Composable
fun ThongKe(navController: NavController? = null) {
    getLayout(navController)
}

@Composable
private fun getLayout(navController: NavController? = null) {

    Scaffold(

        content = {
            ThongKeScreen(it, navController)
        }
    )
}
@Composable
fun ThongKeScreen(paddingValues: PaddingValues, navController: NavController? = null) {

}