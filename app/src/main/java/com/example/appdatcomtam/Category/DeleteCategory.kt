package com.example.appdatcomtam.Category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.R
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XoaLoaiMonAn(viewModel: LoaiMonAnViewModel, navController: NavController) {
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
                            "Xóa loại món ăn",
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
                showXoaLoaiMonAn(viewModel, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showXoaLoaiMonAn(viewModel: LoaiMonAnViewModel, navController: NavController) {
    val showUpdateDialog = remember { mutableStateOf(false) }
    val emty by remember { mutableStateOf("") }
    var inputTenloai by remember { mutableStateOf("") }
    var inputidLoai by remember { mutableStateOf(0) }
    val loaiMons by viewModel.loaiMonAns.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(loaiMons) {
            Card(
                onClick = {
//                    navController.navigate("${ROUTE_NAME_SCREEN.Detail.name}/${ Uri.encode((it.uid.toString()))}/${Uri.encode(it.hoten)}/${Uri.encode(it.mssv)}/${Uri.encode(it.diemTB.toString())}/${Uri.encode(it.daratruong.toString())}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 6.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color(0xFF2F2D2D))
            ) {
                Row(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "" + it.id,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(5.dp),
                        color = Color.White
                    )
                    Text(
                        text = "" + it.tenLoaiMonAn,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(5.dp),
                        color = Color.White
                    )
                    Image(painter = painterResource(id = R.drawable.deleteimages),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                inputidLoai = it.id
                                inputTenloai = it.tenLoaiMonAn.toString()
                                showUpdateDialog.value = true
                            })
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
            if (showUpdateDialog.value) {
                AlertDialog(
                    onDismissRequest = { showUpdateDialog.value = false },
                    confirmButton = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 50.dp)
                                    .height(40.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFFFB703)),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    showUpdateDialog.value = false
                                }
                            ) {
                                Text(text = "Hủy")
                            }
                            Spacer(modifier = Modifier.width(20.dp))

                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 50.dp)
                                    .height(40.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFFFB703)),
                                shape = RoundedCornerShape(10.dp),
                                onClick = {
                                    viewModel.deleteLoaiMonAn(
                                        loaiMonAn = LoaiMonAn(
                                            id = inputidLoai.toInt(),
                                            tenLoaiMonAn = inputTenloai
                                        )
                                    )
                                    showUpdateDialog.value = false
                                }
                            ) {
                                Text(text = "Đồng ý")
                            }

                        }
                    },
                    title = {
                        Text(
                            text = "Xóa tên loại món ăn",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    },
                    text = {
                        Text(
                            text = "Bạn chắc chứ ?",
                            fontSize = 18.sp
                        )
                    }
                )
            }
        }
    }
}