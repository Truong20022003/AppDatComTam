package com.example.appdatcomtam.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Model.User
import com.example.appdatcomtam.Repo.RepoCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoaiMonAnViewModel(val repo: RepoCategory) : ViewModel() {

    fun insertCate() {
        viewModelScope.launch {
            val users = repo.getAll()
            users.collect {
                if (it.isEmpty()) {
                    repo.addLoaiMon(LoaiMonAn(id = 1, tenLoaiMonAn = "Món chính"))
                    repo.addLoaiMon(LoaiMonAn(id = 2, tenLoaiMonAn = "Món phụ"))
                    repo.addLoaiMon(LoaiMonAn(id = 3, tenLoaiMonAn = "Món tráng miệng"))

                }
            }
        }
    }
    fun addLoaiMonAn(loaiMonAn: LoaiMonAn) {
        viewModelScope.launch {
            repo.addLoaiMon(loaiMonAn)
        }
    }

    val loaiMonAns = repo.getAll()

    fun deleteLoaiMonAn(loaiMonAn: LoaiMonAn) {
        viewModelScope.launch {
            repo.deleteLoaiMon(loaiMonAn)
        }
    }

    fun updateLoaiMonAn(loaiMonAn: LoaiMonAn) {
        viewModelScope.launch {
            repo.updateLoaiMon(loaiMonAn)
        }
    }
}
