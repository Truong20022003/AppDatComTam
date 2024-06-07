package com.example.appdatcomtam.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appdatcomtam.Interface.*
import com.example.appdatcomtam.Model.*

@Database(
    entities = [LoaiMonAn::class, MonAn::class, User::class, GioHang::class, DonHang::class, ChiTietDonHang::class],
    version = 1,
    exportSchema = false
)
abstract class DbHelper : RoomDatabase() {

    abstract fun loaiMonAnDao(): LoaiMonAnDao
    abstract fun monAnDao(): MonAnDao
    abstract fun userDao(): UserDao
    abstract fun gioHangDao(): GioHangDao
    abstract fun donHangDao(): DonHangDao
    abstract fun chiTietDonHangDao(): ChiTietDonHangDao

    companion object {
        @Volatile
        private var INSTANCE: DbHelper? = null

        fun getInstance(context: Context): DbHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DbHelper::class.java,
                    "comtam_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}