package com.example.appdatcomtam.Home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.appdatcomtam.Home.Detail_don_hang.DetailViewModel
import com.example.appdatcomtam.Navigation.Screen
import com.example.appdatcomtam.R
import java.time.LocalDate

data class HoaDon(val id: Int, val maDh: String, val gia: Double, var trangthai: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: DetailViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Cum tứm đim",
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.cairo_bold))
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.logoimages),
                        modifier = Modifier.size(60.dp),
                        contentDescription = "logo"
                    )
                },
                actions = {
                    IconButton(onClick = { /* Handle action icon press */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notificationimages),
                            modifier = Modifier.size(30.dp),
                            tint = Color.Yellow,
                            contentDescription = "Notification"
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
                showHomeScreen(navController, viewModel)
            }
        }
    )
}

@Composable
fun showHomeScreen(navController: NavController, viewModel: DetailViewModel) {
    val currentDate = LocalDate.now()
    val hoaDonList by viewModel.hoaDonList.observeAsState(emptyList())

    val demHoaDon = hoaDonList.count { it.trangthai }
    val dk=hoaDonList.filter { it.trangthai===true}
    val tongdoanhthu = dk.sumOf { it.gia }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column {
                Text(
                    text = "Today: $currentDate",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.cairo_bold)),
                    lineHeight = 25.sp
                )
                Text(
                    text = "Số lượng đơn: $demHoaDon",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.cairo_bold)),
                    lineHeight = 25.sp
                )
                Row {
                    Text(
                        text = "Doanh thu:",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        lineHeight = 25.sp
                    )
                    Text(
                        text = "${tongdoanhthu.toInt()}.000đ",
                        color = Color(0xFFFE724C),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        lineHeight = 25.sp
                    )
                }
            }
        }
        items(hoaDonList) { ds ->
            ItemHome(
                maDh = ds.maDh,
                gia = ds.gia,
                trangthai = ds.trangthai,
                Modifier.padding(10.dp),
                onClickItemDetail = {
                    navController.navigate(
                        Screen.DeatailDonHang.route
                                + "/${Uri.encode(ds.id.toString())}"
                    )
                }
            )
        }
    }
}

@Composable
fun ItemHome(
    maDh: String,
    gia: Double,
    trangthai: Boolean,
    modifier: Modifier,
    onClickItemDetail: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
            .padding(20.dp)
            .clickable { onClickItemDetail() }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Đơn hàng $maDh",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.W700,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "||",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Text(
                    text = "${gia.toInt()}.000 đ",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Trạng thái",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.W700,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            Text(
                text = if (trangthai) "Chấp nhận" else "Từ chối",
                color = Color.Red,
                fontSize = 17.sp,
                fontWeight = FontWeight.W700,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
        }
    }
}
