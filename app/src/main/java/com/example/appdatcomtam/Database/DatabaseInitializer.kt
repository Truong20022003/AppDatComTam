package com.example.appdatcomtam.Database

import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Model.MonAn
import com.example.appdatcomtam.Model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseInitializer {
    companion object {
        fun populateDatabase(database: DbHelper) {
            val loaiMonAnDao = database.loaiMonAnDao()
            val monAnDao = database.monAnDao()
            val userDao = database.userDao()
            val gioHangDao = database.gioHangDao()
            val donHangDao = database.donHangDao()
            val chiTietDonHangDao = database.chiTietDonHangDao()

            CoroutineScope(Dispatchers.IO).launch {
                loaiMonAnDao.insert(LoaiMonAn(0, "Nước uống"))
                loaiMonAnDao.insert(LoaiMonAn(1, "Món chính"))

                monAnDao.insert(MonAn(0, 1, 10000.0, "Coca Cola"))
                monAnDao.insert(MonAn(1, 2, 50000.0, "Phở bò"))

                userDao.insert(User(0, "user1", "password1", "Address 1"))
                userDao.insert(User(1, "user2", "password2", "Address 2"))


            }
        }
    }
}
