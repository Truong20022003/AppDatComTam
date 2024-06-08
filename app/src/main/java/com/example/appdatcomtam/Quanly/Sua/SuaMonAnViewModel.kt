package com.example.appdatcomtam.Quanly.Sua

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.MonAn
import kotlinx.coroutines.launch



class SuaMonAnViewModel : ViewModel() {
    var id by mutableStateOf(0)
    var gia by mutableStateOf("")
    var tenMonAn by mutableStateOf("")
    var hinhAnh by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)

    fun onClickUpdate(context: Context, monAn: MonAn) {
        if (gia.isEmpty() || tenMonAn.isEmpty()) {
            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val giaValue = gia.toDouble()
                viewModelScope.launch {
                    val db = DbHelper.getInstance(context)
                    db.monAnDao().update(monAn)
                    Toast.makeText(context, "Cập nhật món ăn thành công", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
