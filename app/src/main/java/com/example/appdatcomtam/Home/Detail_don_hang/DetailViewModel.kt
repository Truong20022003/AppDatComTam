package com.example.appdatcomtam.Home.Detail_don_hang

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Home.HoaDon
import com.example.appdatcomtam.Model.MonAn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DetailViewModel (application: Application) : AndroidViewModel(application)  {
    private val db = DbHelper.getInstance(application)
    val monAnList: StateFlow<List<MonAn>> = db.monAnDao().getAll().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )
    private val _hoaDonList = MutableLiveData<List<HoaDon>>(
        listOf(
            HoaDon(1, "Ct11", 300.000, true),
            HoaDon(2, "Ct1331", 330.000, false),
            HoaDon(3, "Ct1221", 200.000, true),
            HoaDon(4, "Ct11", 700.000, false),
        )
    )
    val hoaDonList: MutableLiveData<List<HoaDon>> = _hoaDonList
    fun updateStatus(id: Int, newStatus: Boolean) {
        val updatedList = _hoaDonList.value?.map { hoaDon ->
            if (hoaDon.id == id) {
                hoaDon.copy(trangthai = newStatus)
            } else {
                hoaDon
            }
        }
        _hoaDonList.value = updatedList!!
        logCurrentStatus(id, newStatus)
    }
    private fun logCurrentStatus(id: Int, newStatus: Boolean) {
        Log.d("DetailViewModel", "Hóa Đơn ID: $id, Trạng Thái: $newStatus")
        // Nếu không sử dụng Android, bạn có thể dùng println
        // println("Hóa Đơn ID: $id, Trạng Thái: $newStatus")
    }
}
