package com.example.appdatcomtam.Quanly.MonAn.Them

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.MonAn
import com.example.appdatcomtam.Quanly.MonAn.copyUriToInternalStorage
import com.example.appdatcomtam.Repo.RepoCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

//class ThemMonAnViewModel(val repo: RepoCategory) : ViewModel() {
//    var gia by mutableStateOf("")
//    var tenMonAn by mutableStateOf("")
//    var selectedOptionText by mutableStateOf("")
//    var idloai by mutableStateOf<Int?>(null)
//    var imageUri by mutableStateOf<Uri?>(null)
//    var imagePath by mutableStateOf<String?>(null)
//    var check = false
//    val loaiMonAns = ""
////    fun onClickAdd(context: Context) {
////        if (gia.isEmpty() || tenMonAn.isEmpty() || selectedOptionText.isEmpty() || imageUri == null || idloai == null) {
////            Toast.makeText(context, "không được để trống", Toast.LENGTH_SHORT).show()
////        } else {
////            try {
////                val giaValue = gia.toDouble()
////                Log.d("ThemMonAn", "id loai mon an: $idloai, Loại món: $selectedOptionText, Giá: $gia, Tên món ăn: $tenMonAn, anh: $imageUri")
////
////                val internalImagePath = copyUriToInternalStorage(context, imageUri!!)
////                val monAn = MonAn(idLoaiMonAn = idloai.toString(), gia = giaValue, tenMonAn = tenMonAn, hinhAnh = internalImagePath)
////
////                viewModelScope.launch(Dispatchers.IO) {
////                    val db = DbHelper.getInstance(context)
////                    db.monAnDao().insert(monAn)
////                }
////                tenMonAn = ""
////                gia = ""
////                idloai = null
////                imageUri = null
////                imagePath = null
////                check = true
////                Toast.makeText(context, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show()
////            } catch (e: NumberFormatException) {
////                Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show()
////            }
////        }
////    }
//}
class ThemMonAnViewModel : ViewModel() {
    var gia by mutableStateOf("")
    var idloai by mutableStateOf("")
    var tenMonAn by mutableStateOf("")
    var selectedOptionText by mutableStateOf("")
    var imageUri by mutableStateOf<Uri?>(null)
    var imagePath by mutableStateOf<String?>(null)
    var check = false

    fun onClickAdd(context: Context) {
        if (gia.isEmpty() || tenMonAn.isEmpty() || selectedOptionText.isEmpty() || imageUri == null) {
            Toast.makeText(context, "không được để trống", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val giaValue = gia.toDouble()
                Log.d("ThemMonAn", "id loai mon an: $idloai ,Loại món: $selectedOptionText, Giá: $gia, Tên món ăn: $tenMonAn, anh: $imageUri")

                val internalImagePath = copyUriToInternalStorage(context, imageUri!!)
                val monAn = MonAn(idLoaiMonAn = idloai, gia = giaValue, tenMonAn = tenMonAn, hinhAnh = internalImagePath)

                viewModelScope.launch(Dispatchers.IO) {
                    val db = DbHelper.getInstance(context)
                    db.monAnDao().insert(monAn)
                }

                tenMonAn = ""
                gia = ""
                idloai =""
                imageUri = null
                imagePath = null
                check = true
                Toast.makeText(context, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Giá phải là số", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
