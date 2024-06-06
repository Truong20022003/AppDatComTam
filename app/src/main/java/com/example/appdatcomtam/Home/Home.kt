package com.example.appdatcomtam.Home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdatcomtam.R
import java.time.LocalDate

private data class HoaDon(val maDh: String, val gia: Double, val trangthai: Boolean)

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
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
                }
                , colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF252121)
                )
                , modifier = Modifier.shadow(10.dp, RoundedCornerShape(10.dp), spotColor = Color.Black),
            )
        },

        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Color(0xFF252121))
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                showHomeScreen()
            }
        }
    )
}

@Composable
fun showHomeScreen() {
    val currentDate = LocalDate.now()
    val hoadon = listOf(
        HoaDon("Ct11", 300.000, true),
        HoaDon("Ct11", 300.000, false),
        HoaDon("Ct11", 300.000, true),
        HoaDon("Ct11", 300.000, false),
    )
    val demHoaDon = hoadon.count { it.trangthai }
    val tongdoanhthu = hoadon.sumOf { it.gia }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
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
                        text = "$tongdoanhthu đ",
                        color = Color(0xFFFE724C),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W700,
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        lineHeight = 25.sp
                    )
                }
            }
        }
        items(hoadon) { ds ->
            ItemHome(
                maDh = ds.maDh,
                gia = ds.gia,
                trangthai = ds.trangthai,
                Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun ItemHome(maDh: String, gia: Double, trangthai: Boolean, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF2F2D2D),shape = RoundedCornerShape(10.dp))
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Đơn hàng $maDh",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.W700,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            Row(modifier=Modifier.fillMaxWidth(0.5f), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "||",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Text(
                        text = "$gia đ",
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
