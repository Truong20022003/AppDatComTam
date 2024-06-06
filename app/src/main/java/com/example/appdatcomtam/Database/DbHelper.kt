package com.example.appdatcomtam.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appdatcomtam.Interface.ChiTietDonHangDao
import com.example.appdatcomtam.Interface.DonHangDao
import com.example.appdatcomtam.Interface.GioHangDao
import com.example.appdatcomtam.Interface.LoaiMonAnDao
import com.example.appdatcomtam.Interface.MonAnDao
import com.example.appdatcomtam.Interface.UserDao
import com.example.appdatcomtam.Model.ChiTietDonHang
import com.example.appdatcomtam.Model.DonHang
import com.example.appdatcomtam.Model.GioHang
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Model.MonAn
import com.example.appdatcomtam.Model.User

@Database(entities = [LoaiMonAn::class, MonAn::class, User::class, GioHang::class, DonHang::class, ChiTietDonHang::class], version = 1)
abstract class DbHelper : RoomDatabase() {
    abstract fun loaiMonAnDao(): LoaiMonAnDao
    abstract fun monAnDao(): MonAnDao
    abstract fun userDao(): UserDao
    abstract fun gioHangDao(): GioHangDao
    abstract fun donHangDao(): DonHangDao
    abstract fun chiTietDonHangDao(): ChiTietDonHangDao

    companion object {
        @Volatile private var instance: DbHelper? = null

        fun getDatabase(context: Context): DbHelper =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, DbHelper::class.java, "comtam_database")
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                        DatabaseInitializer.populateDatabase(getDatabase(context))
//                    }
//                })
//                .build()
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, DbHelper::class.java, "comtam_database")
                .build()
    }
}
