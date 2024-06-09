package com.example.appdatcomtam.Quanly.Sua

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.MonAn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File

class DanhSachViewModel(application: Application) : AndroidViewModel(application) {
    private val db = DbHelper.getInstance(application)
    val monAnList: StateFlow<List<MonAn>> = db.monAnDao().getAll().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun deleteMonAn(monAn: MonAn) {
        viewModelScope.launch {
            // Xóa món ăn từ cơ sở dữ liệu
            Log.d("xoaMonAn", "id mnon an : ${monAn.id}, id loai mon an: ${monAn.idLoaiMonAn} , Giá: ${monAn.gia}, Tên món ăn: ${monAn.tenMonAn}, anh: ${monAn.hinhAnh}")
            db.monAnDao().delete(monAn)
            // Xóa tệp ảnh nếu tồn tại
            val imageUri = Uri.parse(monAn.hinhAnh)
            val imageFile = File(imageUri.path)
            if (imageFile.exists()) {
                imageFile.delete()
            }
        }
    }
}
