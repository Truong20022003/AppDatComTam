package com.example.appdatcomtam.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appdatcomtam.Repo.RepoCategory

class LoaiMonAnViewModelFactory(private val repo: RepoCategory) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoaiMonAnViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoaiMonAnViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

