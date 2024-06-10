package com.example.appdatcomtam.Category

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Navigation.Screen
import com.example.appdatcomtam.R
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModel


@Composable
fun LoaiMonAnListScreen(navController: NavController, myViewModel: LoaiMonAnViewModel) {
    val context = LocalContext.current
    var itShowDiaLog by remember { mutableStateOf(false) }

    if (itShowDiaLog) {
        getDialog(viewModel = myViewModel, onConfirmation = {itShowDiaLog = false},context)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF252121)),
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Divider(
                color = Color.Black,
                thickness = 3.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(18.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {
                        itShowDiaLog = true
                    },
                contentAlignment = Alignment.TopStart
            ) {
                Row(
                    modifier = Modifier
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logoimages),
                        contentDescription = null,
                        modifier = Modifier
                            .width(70.dp)
                            .height(58.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Thêm loai món ăn",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {
                        navController.navigate(
                            route = Screen.EditLoaiMonAnScreen.route
                        )
                    },
                contentAlignment = Alignment.TopStart
            ) {
                Row(
                    modifier = Modifier
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logoimages),
                        contentDescription = null,
                        modifier = Modifier
                            .width(70.dp)
                            .height(58.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Sửa loai món ăn",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {
                        navController.navigate(Screen.DeleteLoaiMonan.route)
                    },
                contentAlignment = Alignment.TopStart
            ) {
                Row(
                    modifier = Modifier
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.logoimages),
                        contentDescription = null,
                        modifier = Modifier
                            .width(70.dp)
                            .height(58.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Xóa loai món ăn",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun getDialog(viewModel: LoaiMonAnViewModel,onConfirmation: () -> Unit, context: Context) {
    var inputTenLoaiMonAn by remember { mutableStateOf("") }

    val emty by remember { mutableStateOf("") }


    AlertDialog(onDismissRequest = { },
        dismissButton = {
            Button(onClick = {
                onConfirmation()
                inputTenLoaiMonAn = emty
            }) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            if(inputTenLoaiMonAn.isNotEmpty()){
                Button(onClick = {
                    viewModel.addLoaiMonAn(
                        LoaiMonAn(
                            0,
                            inputTenLoaiMonAn
                        )
                    )
                    onConfirmation()
                    inputTenLoaiMonAn = emty
                    Toast.makeText(context,"Them thanh cong", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Save")
                }
            }
        },
        title = {
            Text(text = "Them loai mon an",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(5.dp))
        },
        text = {
            Column {
                OutlinedTextField(value = inputTenLoaiMonAn, onValueChange = {inputTenLoaiMonAn=it},
                    label = {
                        Text(text = "Ten loai mon")
                    },
                    placeholder = { Text(text = "Nhap ten loai mon")})
            }
        }
    )

}