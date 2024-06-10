package com.example.appdatcomtam.Quanly.MonAn.Sua

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.appdatcomtam.Model.MonAn
import com.example.appdatcomtam.Quanly.MonAn.copyUriToInternalStorage
import com.example.appdatcomtam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuaMonAnScreen(
    viewModel: SuaMonAnViewModel,
    navController: NavController,
    id: String?,
    idLoaiMonAn: String,
    tenMonAn: String,
    gia: String,
    hinhAnh: String,
) {
    LaunchedEffect(Unit) {
        viewModel.initialize(id, idLoaiMonAn, tenMonAn, gia, hinhAnh)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "back",
                                tint = Color.White,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.logoimages),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 8.dp)
                        )
                        Text(
                            "Sửa món ăn",
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.cairo_bold)),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp)
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
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Color(0xFF252121))
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                showSuaMonAnScreen(viewModel, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showSuaMonAnScreen(
    viewModel: SuaMonAnViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val newImagePath = copyUriToInternalStorage(context, it)
            viewModel.hinhAnh = newImagePath // Update the image path
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val painter = rememberAsyncImagePainter(
            model = viewModel.hinhAnh.takeIf { it.isNotEmpty() }///sử lý nếu null
        )
        Image(
            painter = painter,
            contentDescription = "selected_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .shadow(
                    10.dp,
                    RoundedCornerShape(10.dp),
                    spotColor = Color.White
                )
                .clickable { imagePickerLauncher.launch("image/*") }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Tên món ăn",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold)),
                modifier = Modifier.padding(start = 8.dp)
            )
            OutlinedTextField(
                value = viewModel.tenMonAn,
                onValueChange = { viewModel.tenMonAn = it },
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
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold)),
                modifier = Modifier.padding(start = 8.dp)
            )
            TextField(
                value = viewModel.gia,
                onValueChange = { newValue -> viewModel.gia = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
        }

        Button(
            onClick = {
                val datanew = MonAn(
                    id = viewModel.id.toInt(),
                    idLoaiMonAn = viewModel.idLoaiMonAn,
                    tenMonAn = viewModel.tenMonAn,
                    gia = viewModel.gia.toDouble(),
                    hinhAnh = viewModel.hinhAnh
                )
                viewModel.onClickUpdate(context, datanew)
                if (viewModel.check) navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(top = 50.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFB703)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Cập nhật", fontSize = 20.sp)
        }
    }
}
