package com.example.appdatcomtam.Quanly.Them

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemMonAnViewModel : ViewModel() {
    var gia by mutableStateOf("")
    var tenMonAn by mutableStateOf("")
    var selectedOptionText by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)

    fun onClickAdd(context: Context) {
        if (gia.isEmpty() || tenMonAn.isEmpty()) {
            Toast.makeText(context, "không được để trống", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val giaValue = gia.toDouble()
                Log.d(
                    "ThemMonAn",
                    "Loại món: $selectedOptionText, Giá: $gia, Tên món ăn: $tenMonAn"
                )
                Toast.makeText(context, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
