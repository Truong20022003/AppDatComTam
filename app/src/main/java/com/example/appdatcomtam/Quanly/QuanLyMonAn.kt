package com.example.appdatcomtam.Quanly

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdatcomtam.Quanly.Sua.DanhSachActivity
import com.example.appdatcomtam.Quanly.Sua.DanhSachScreen
import com.example.appdatcomtam.Quanly.Sua.SuaMonAnActivity
import com.example.appdatcomtam.Quanly.Them.ThemMonAnActivity
import com.example.appdatcomtam.R


class QuanLyMonAn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLyMonAnScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuanLyMonAnScreen() {
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
                showQuanLyMonAnScreen()
            }
        }
    )
}

@Composable
fun showQuanLyMonAnScreen() {
    val context = LocalContext.current
    Column {
        NutBam(
            text = "Thêm món ăn",
            OnclickItem = { context.startActivity(Intent(context, ThemMonAnActivity::class.java)) })
        NutBam(
            text = "Sửa món ăn",
            OnclickItem = { context.startActivity(Intent(context, DanhSachActivity()::class.java)) })
        NutBam(text = "Xóa món ăn", OnclickItem = {})
    }
}

@Composable
fun NutBam(text: String, OnclickItem: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .clickable { OnclickItem() }) {
        Image(
            painter = painterResource(id = R.drawable.logoimages),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 17.sp,
            fontWeight = FontWeight.W700,
            fontFamily = FontFamily(Font(R.font.cairo_bold)),
            lineHeight = 22.sp
        )
    }
}

