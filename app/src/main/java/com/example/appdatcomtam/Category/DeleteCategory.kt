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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.R
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XoaLoaiMonAn(viewModel: LoaiMonAnViewModel, navController: NavController) {
    val showUpdateDialog = remember { mutableStateOf(false) }
    val emty by remember { mutableStateOf("") }
    var inputTenloai by remember { mutableStateOf("") }
    var inputidLoai by remember { mutableStateOf(0) }
    val loaiMons by viewModel.loaiMonAns.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252121))
    ) {
//        HeaderQL(navController){
//            navController.popBackStack()
        }

        Row (
            modifier = Modifier
                .padding(start = 24.dp, top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Id",
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Name",
                fontSize = 20.sp,
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(40.dp))
        }

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
                    colors = CardDefaults.cardColors(Color.DarkGray)
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
                        Image(painter = painterResource(id = R.drawable.deleteimages), contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    inputidLoai = it.id
                                    inputTenloai = it.tenLoaiMonAn.toString()
                                    showUpdateDialog.value = true
                                })
                    }
                    Spacer(modifier = Modifier.width(10.dp) )

                }
                if (showUpdateDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showUpdateDialog.value = false },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showUpdateDialog.value = false


                                }
                            ) {
                                Text(text = "No")
                            }
                        },
                        confirmButton = {

                            Button(
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
                                Text(text = "Yes")
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
                                fontSize = 25.sp
                            )
                        }
                    )
                }
            }
        }
    }
