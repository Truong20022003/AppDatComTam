package com.example.appdatcomtam.Category

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Model.MonAn
import com.example.appdatcomtam.Repo.RepoCategory
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModel
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModelFactory


@Composable
fun AddLoaiMonAnScreen(viewModel: LoaiMonAnViewModel, navController: NavController) {

    val emty by remember { mutableStateOf("") }
    var inputTenloai by remember { mutableStateOf("") }
    var inputidLoai by remember { mutableStateOf(0) }
    val loaiMons by viewModel.loaiMonAns.collectAsState(initial = emptyList())

    var tenLoaiMonAn by remember { mutableStateOf("") }
    val context = LocalContext.current
//    val monAn = MonAn(idLoaiMonAn = idloai, gia = giaValue, tenMonAn = tenMonAn, hinhAnh = internalImagePath)
    val db = DbHelper.getInstance(context)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = tenLoaiMonAn,
            onValueChange = { tenLoaiMonAn = it },
            label = { Text("Tên loại món ăn") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (tenLoaiMonAn.isNotBlank()) {
                    viewModel.addLoaiMonAn(LoaiMonAn(0, tenLoaiMonAn))
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm loại món ăn")
        }
    }
}

