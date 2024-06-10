package com.example.appdatcomtam.Home.Detail_don_hang

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.appdatcomtam.R
import java.time.LocalTime
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel, id: String) {
    Log.d("id detail", "id: $id")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logoimages),
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 8.dp),
                            contentDescription = "logo"
                        )
                        Text(
                            "Cum tứm đim",
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.cairo_bold))
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF252121)
                ),
                modifier = Modifier.shadow(
                    30.dp,
                    spotColor = Color.Black
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(Color(0xFF252121))
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                showDetailScreen(navController, viewModel, id.toInt())
            }
        }
    )
}

@Composable
fun showDetailScreen(navController: NavController, viewModel: DetailViewModel, id: Int) {
    LazyColumn(modifier = Modifier.padding(15.dp)) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        viewModel.updateStatus(id, true)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2F2D2D)),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Xác nhận", fontSize = 17.sp)
                }
                Button(
                    onClick = {
                        viewModel.updateStatus(id, false)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2F2D2D)),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Hủy", fontSize = 17.sp)
                }
            }
        }
        val muc = listOf(Muc("Món chính"), Muc("Món phụ"), Muc("Topping"), Muc("Khác"))
        items(muc) { ds ->
            ItemDetail(ds, viewModel = viewModel)
            Divider(color = Color.Gray, thickness = 1.dp) // Thêm gạch dưới sau mỗi item
        }
        item {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Số nhà:  54",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Text(
                    text = "Đường:  14",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Text(
                    text = "Phường:   Đông Hưng Thuận",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Text(
                    text = "Quận:  12",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Text(
                    text = "Thành phố:  Hồ Chí Minh",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cairo_bold))
                )
                Divider(color = Color.White, thickness = 1.5.dp) // Thêm gạch dưới sau mỗi item
            }
        }
        item {
           Bill(viewModel)
            }
        }
    }


data class Muc(val name: String)


@Composable
fun ItemDetail(ds: Muc, viewModel: DetailViewModel) {
    val monAnList by viewModel.monAnList.collectAsState()
    Column() {
        Text(
            text = ds.name,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        monAnList.forEachIndexed { index, monAn ->
            val soNgauNhien: Int = Random.nextInt(0, 10) // Tạo một số nguyên ngẫu nhiên từ 0 đến 9
            val soNgauNhienCoSo0: String = String.format("%02d", soNgauNhien)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .background(Color(0xFF2F2D2D), shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(0.8f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "${index + 1}",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.cairo_bold))
                    )
                    Image(
                        painter = rememberImagePainter(data = monAn.hinhAnh),
                        contentDescription = "selected_image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = RoundedCornerShape(50.dp)),
                        contentScale = ContentScale.Crop,
                    )

                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                ) {
                    monAn.tenMonAn?.let {
                        Text(
                            text = it,
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Text(
                        text = "${monAn.gia.toInt()}K",
                        fontSize = 15.sp,
                        color = Color(0xFFFE724C),
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    text = "${soNgauNhienCoSo0}",
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun Bill(viewModel: DetailViewModel) {
    val now = LocalTime.now()
    val gio = now.hour // Lấy giờ
    val phut = now.minute // Lấy phút
    val monAnList by viewModel.monAnList.collectAsState()
    val tongsomon=monAnList.size
    val tongtien=monAnList.sumOf { it.gia }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Giờ:  ${gio}h${phut}p",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Text(
            text = "SĐT:  09636964444",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Text(
            text = "Tổng số lượng món ăn:   ${tongsomon}",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Text(
            text = "Tổng lượng số món ăn thêm:  ${tongsomon}",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Text(
            text = "Tổng số lượng topping:  ${tongsomon}",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Text(
            text = "Tổng số lượng khác:  ${tongsomon}",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        Text(
            text = "Tổng tiền:  ${tongtien.toInt()}K",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
    }
}