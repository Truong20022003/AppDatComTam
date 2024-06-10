package com.example.appdatcomtam.Quanly.MonAn.Sua

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.appdatcomtam.Navigation.Screen
import com.example.appdatcomtam.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DanhSachScreen(viewModel: DanhSachViewModel, navController: NavController) {
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
                showDanhSachMonAn(viewModel, navController)
            }
        }
    )
}


@Composable
fun showDanhSachMonAn(viewModel: DanhSachViewModel, navController: NavController) {
    val monAnList by viewModel.monAnList.collectAsState()
    LazyColumn {
        items(monAnList) { ds ->
            ItemDanhSachMonAn(
                id = ds.id,
                gia = ds.gia,
                tenMonAn = ds.tenMonAn,
                onDelete = { viewModel.deleteMonAn(ds) },
                onEdit = {
                    navController.navigate(
                        Screen.SuaMonAn.route
                                + "/${Uri.encode(ds.id.toString())}"
                                + "/${Uri.encode(ds.idLoaiMonAn)}"
                                + "/${Uri.encode(ds.tenMonAn)}"
                                + "/${Uri.encode(ds.gia.toString())}"
                                + "/${Uri.encode(ds.hinhAnh)}"
                    )
                },
                hinhAnh = ds.hinhAnh,
                navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDanhSachMonAn(
    id: Int,
    gia: Double,
    tenMonAn: String?,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    hinhAnh: String?,
    navController: NavController
) {
    val context = LocalContext.current
    val showDialogxoa = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(75.dp)
            .background(Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = id.toString(),
            textAlign = TextAlign.Start,
            fontSize = 17.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Image(
            painter = rememberImagePainter(data = hinhAnh),
            contentDescription = "selected_image",
            modifier = Modifier
                .size(40.dp)
                .clip(shape = RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop,
        )
        Column(
            Modifier
                .fillMaxHeight()
        ) {
            tenMonAn?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Start,
                    fontSize = 17.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
            }
            Text(
                text = "${gia.toInt()}K",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = Color(0xFFFE724C),
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
        }
        IconButton(onClick = onEdit) {
            Icon(
                painter = painterResource(id = R.drawable.editimages),
                modifier = Modifier.size(20.dp),
                tint = Color.White,
                contentDescription = "edit"
            )
        }
        IconButton(onClick = { showDialogxoa.value = true }) {
            Icon(
                painter = painterResource(id = R.drawable.deleteimages),
                modifier = Modifier.size(20.dp),
                tint = Color.White,
                contentDescription = "delete"
            )
        }
    }

    if (showDialogxoa.value) {
        AlertDialog(
            onDismissRequest = { showDialogxoa.value = false },
            title = {
                Text(
                    text = "Thông Báo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.cairo_bold)),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Text(
                    text = "Bạn có chắc muốn xóa món ăn \n ' $tenMonAn '?",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFB703)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            showDialogxoa.value = false
                        },
                        modifier = Modifier.widthIn(100.dp)
                    ) {
                        Text(
                            text = "Hủy",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(Font(R.font.cairo_bold))
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFFFB703)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.widthIn(100.dp),
                        onClick = {
                            onDelete()
                            showDialogxoa.value = false
                        }
                    ) {
                        Text(
                            text = "Xác nhận",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(Font(R.font.cairo_bold))
                        )
                    }
                }
            }
        )
    }
}
