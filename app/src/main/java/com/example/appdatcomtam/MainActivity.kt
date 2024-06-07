package com.example.appdatcomtam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Home.HomeScreen
import com.example.appdatcomtam.Navigation.ScreenNav
//import com.example.appdatcomtam.Home.Home
//import com.example.appdatcomtam.Home.HomeScreen
import com.example.appdatcomtam.Quanly.QuanLyMonAnScreen
import com.example.appdatcomtam.R
//import com.example.appdatcomtam.Home.Home
//import com.example.appdatcomtam.Home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//HomeScreen()
            ScreenNav()
//            QuanLyMonAnScreen()
//            GreetingPreview()
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.notificationimages), // Ensure this resource exists
        modifier = Modifier.size(60.dp),
        contentDescription = "logo"
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AppDatComTamTheme {
//        Greeting("Android")
//    }
