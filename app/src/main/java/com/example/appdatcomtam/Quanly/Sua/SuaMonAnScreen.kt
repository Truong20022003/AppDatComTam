package com.example.appdatcomtam.Quanly.Sua

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.appdatcomtam.Quanly.MonAn
import com.example.appdatcomtam.Quanly.Them.showThemMonAnScreen
import com.example.appdatcomtam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuaMonAnScreen(viewModel: SuaMonAnViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { if (context is SuaMonAnActivity) context.finish() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                modifier = Modifier.size(25.dp),
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
                            "Sửa món ăn",
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
                showSuaMonAnScreen(viewModel)
//            showDanhSachMonAn()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showSuaMonAnScreen(viewModel: SuaMonAnViewModel) {
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.imageUri = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        viewModel.imageUri?.let {
            Image(
                painter = rememberImagePainter(data = it),
                contentDescription = "selected_image",
                modifier = Modifier
                    .size(200.dp)
                    .clickable { imagePickerLauncher.launch("image/*") }
            )
        } ?: Image(
            painter = painterResource(id = R.drawable.them_hinh_anh),
            contentDescription = "hinh_anh",
            modifier = Modifier
                .size(200.dp)
                .clickable { imagePickerLauncher.launch("image/*") }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Tên món ăn",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 8.dp),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            OutlinedTextField(
                value = viewModel.tenMonAn,
                onValueChange = { newValue ->
                    viewModel.tenMonAn = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Giá",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 8.dp),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            TextField(
                value = viewModel.gia,
                onValueChange = { newValue ->
                    viewModel.gia = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
        }


        Button(
            onClick = {
                viewModel.onClickAdd(context)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(top = 50.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFB703)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Thêm", fontSize = 20.sp)
        }
    }
}


