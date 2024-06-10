package com.example.appdatcomtam.Repo

import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.Model.LoaiMonAn
import kotlinx.coroutines.flow.Flow

class RepoCategory(val loaiMonDB : DbHelper) {
    fun getAll() = loaiMonDB.loaiMonAnDao().getAll()

    suspend fun addLoaiMon(loaiMonAnModel: LoaiMonAn){
        loaiMonDB.loaiMonAnDao().insert(loaiMonAnModel)
    }

    suspend fun deleteLoaiMon(loaiMonAnModel: LoaiMonAn){
        loaiMonDB.loaiMonAnDao().delete(loaiMonAnModel)
    }

    suspend fun updateLoaiMon(loaiMonAnModel: LoaiMonAn){
        loaiMonDB.loaiMonAnDao().update(loaiMonAnModel)
    }

    fun getLoaiMonByID(idLoaiMon : Int){
        loaiMonDB.loaiMonAnDao().getById(idLoaiMon)
    }
}
