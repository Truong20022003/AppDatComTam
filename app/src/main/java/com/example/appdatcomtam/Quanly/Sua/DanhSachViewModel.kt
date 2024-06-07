package com.example.appdatcomtam.Quanly.Sua

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.MonAn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DanhSachViewModel(application: Application) : AndroidViewModel(application) {
    private val db = DbHelper.getInstance(application)
    val monAnList: StateFlow<List<MonAn>> = db.monAnDao().getAll().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun deleteMonAn(monAn: MonAn) {
        viewModelScope.launch {
            db.monAnDao().delete(monAn)
        }
    }
}
