package com.example.appdatcomtam.LichSuaDon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appdatcomtam.R
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LichSuScreen(navController: NavController, viewModel: LichSuViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Lịch sử",
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
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
                    spotColor = Color(0xFF666464)
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
                showLichSuScreen(viewModel, navController)
            }
        }
    )
}

data class DataLichSu(
    val trangThai: Boolean,
    val soLuongMon: Int,
    val ngayDat: String,
    val tienDat: String,
    val gioDat: String
)

@Composable
fun showLichSuScreen(viewModel: LichSuViewModel, navController: NavController) {
    val currentDate = LocalDate.now()
    val now = LocalTime.now()
    val gio = now.hour // Lấy giờ
    val phut =now.minute
    val giovaphut= "$gio:$phut"
    val dataLichSu = listOf(
        DataLichSu(true, 2, currentDate.toString(), "100", giovaphut),
        DataLichSu(false, 1, currentDate.toString(), "400", giovaphut),
        DataLichSu(true, 1, currentDate.toString(), "100", giovaphut),
        DataLichSu(false, 1, currentDate.toString(), "1000", giovaphut),
        DataLichSu(true, 1, currentDate.toString(), "500", "12:00"),
        DataLichSu(false, 1, currentDate.toString(), "100", giovaphut),
        DataLichSu(true, 1, currentDate.toString(), "100", "12:00"),
        DataLichSu(false, 1, currentDate.toString(), "1000", giovaphut),
    )
    LazyColumn(
        modifier = Modifier.padding(16.dp) // Optional padding if needed
    ) {

        items(dataLichSu) { dataLichSu -> ItemLichSu(dataLichSu) }
    }
}

@Composable
fun ItemLichSu(dataLichSu: DataLichSu) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = if (dataLichSu.trangThai) "Đơn hàng đã chấp nhận " else "Đơn hàng chưa chấp nhận",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = Color.Red,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.cairo_regular))
            )
            Row {
                Text(
                    text = dataLichSu.ngayDat,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(R.font.cairo_regular))
                )
                Text(
                    text = dataLichSu.gioDat,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 10.dp),
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(R.font.cairo_regular))
                )
            }
        }
        Column {
            Text(
                text = "${dataLichSu.soLuongMon} món",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.cairo_regular))
            )
            Text(
                text = "${dataLichSu.tienDat}K",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.cairo_regular))
            )
        }

    }
}
