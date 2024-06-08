package com.example.appdatcomtam.Quanly.Them

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.MonAn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThemMonAnViewModel : ViewModel() {
    var gia by mutableStateOf("")
    var tenMonAn by mutableStateOf("")
    var selectedOptionText by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)

    fun onClickAdd(context: Context) {
        if (gia.isEmpty() || tenMonAn.isEmpty() || selectedOptionText.isEmpty()) {
            Toast.makeText(context, "không được để trống", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val giaValue = gia.toDouble()
                Log.d("ThemMonAn", "Loại món: $selectedOptionText, Giá: $gia, Tên món ăn: $tenMonAn,anh: $imageUri")

                val monAn = MonAn(idLoaiMonAn = selectedOptionText, gia = giaValue, tenMonAn = tenMonAn, hinhAnh = imageUri.toString())

                // Thêm món ăn vào cơ sở dữ liệu trong coroutine
                viewModelScope.launch(Dispatchers.IO) {
                    val db = DbHelper.getInstance(context)
                    db.monAnDao().insert(monAn)
                }

                Toast.makeText(context, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
