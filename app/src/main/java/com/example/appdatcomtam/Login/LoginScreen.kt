package com.example.appdatcomtam.Login

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            Toa
        }
    }

    Column(
        modifier = Modifier
            .background(Color(0xFF252121))
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoimages),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 16.dp)
        )
        Text(
            "Welcome",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(vertical = 30.dp)
        )

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
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                disabledTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedSupportingTextColor = Color.White
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
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                disabledTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedSupportingTextColor = Color.White
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
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(top = 50.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFB703)),
            shape = RoundedCornerShape(10.dp),
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
