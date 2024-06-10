package com.example.appdatcomtam.Login

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Navigation.Screen
import com.example.appdatcomtam.R
import com.example.appdatcomtam.ViewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController? = null, context: Context) {
    val db = DbHelper.getInstance(context)
    val userDao = db.userDao()

    // Sử dụng remember để tạo một instance của LoginViewModel
    val viewModel = remember { LoginViewModel(userDao) }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    val isAuthenticated by viewModel.isAuthenticated.observeAsState()

//    LaunchedEffect(isAuthenticated) {
//        if (isAuthenticated == true) {
//            navController?.navigate(Screen.MyBottombar.route) {
//                popUpTo(Screen.Login.route) { inclusive = true }
//            }
//        } else if (isAuthenticated == false) {
//            Log.d("Login", "Login failed")
//        }
//    }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated == true) {

            navController?.navigate(Screen.MyBottombar.route)
        } else if (isAuthenticated == false) {
            Log.d("aaaaaaa", "loiiiiiiiii")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoimages),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 16.dp)
        )
        Text("Welcome", fontSize = 24.sp, color = Color.White, modifier = Modifier.padding(vertical = 30.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Tên đăng nhập") },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable { rememberMe = !rememberMe }
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text("Nhớ mật khẩu", color = Color.White)
        }

        Button(
            onClick = { viewModel.login(username, password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Đăng nhập")
        }

        Text(
            "Bạn chưa có tài khoản?",
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
