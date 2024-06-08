package com.example.appdatcomtam.Quanly.Sua

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import com.example.appdatcomtam.Navigation.Screen
import com.example.appdatcomtam.R

//class DanhSachActivity : ComponentActivity() {
//    private val viewModel: DanhSachViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DanhSachScreen(viewModel)
//        }
//    }
//}

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
                        Screen.SuaMonAn.route + "/${Uri.encode(ds.id.toString())}" + "/${
                            Uri.encode(
                                ds.tenMonAn
                            )
                        }" + "/${Uri.encode(ds.gia.toString())}"
                        + "/${Uri.encode(ds.hinhAnh)}"
                    )
                }, navController
            )
        }
    }
}

@Composable
fun ItemDanhSachMonAn(
    id: Int,
    gia: Double,
    tenMonAn: String?,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
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
                text = gia.toString(),
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
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.deleteimages),
                modifier = Modifier.size(20.dp),
                tint = Color.White,
                contentDescription = "delete"
            )
        }
    }
}
