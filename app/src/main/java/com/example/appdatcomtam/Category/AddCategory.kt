package com.example.appdatcomtam.Category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.R
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLoaiMonAnScreen(viewModel: LoaiMonAnViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                modifier = Modifier.size(20.dp),
                                tint = Color.White,
                                contentDescription = "back"
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.logoimages),
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 8.dp),
                            contentDescription = "logo"
                        )
                        Text(
                            "Thêm loại món ăn",
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.cairo_bold))
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF252121)
                ),
                modifier = Modifier.shadow(
                    10.dp,
                    RoundedCornerShape(10.dp),
                    spotColor = Color.Black
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Color(0xFF252121))
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                showAddLoaiMonAn(viewModel, navController)
            }
        }
    )

}
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun showAddLoaiMonAn(viewModel: LoaiMonAnViewModel, navController: NavController) {
    val emty by remember { mutableStateOf("") }
    var inputTenloai by remember { mutableStateOf("") }
    var inputidLoai by remember { mutableStateOf(0) }
    val loaiMons by viewModel.loaiMonAns.collectAsState(initial = emptyList())

    var tenLoaiMonAn by remember { mutableStateOf("") }
    val context = LocalContext.current
//    val monAn = MonAn(idLoaiMonAn = idloai, gia = giaValue, tenMonAn = tenMonAn, hinhAnh = internalImagePath)
    val db = DbHelper.getInstance(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = tenLoaiMonAn,
            onValueChange = { tenLoaiMonAn = it },
            label = { Text("Tên loại món ăn") },
            modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            disabledTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        )

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {
                if (tenLoaiMonAn.isNotBlank()) {
                    viewModel.addLoaiMonAn(LoaiMonAn(0, tenLoaiMonAn))
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm loại món ăn")
        }
    }
}
