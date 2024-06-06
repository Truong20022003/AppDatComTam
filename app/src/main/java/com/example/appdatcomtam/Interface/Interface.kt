package com.example.appdatcomtam.Interface

import androidx.room.*
import com.example.appdatcomtam.Model.ChiTietDonHang
import com.example.appdatcomtam.Model.DonHang
import com.example.appdatcomtam.Model.GioHang
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Model.MonAn
import com.example.appdatcomtam.Model.User

@Dao
interface LoaiMonAnDao {
    @Query("SELECT * FROM LoaiMonAn")
    fun getAll(): List<LoaiMonAn>

    @Query("SELECT * FROM LoaiMonAn WHERE id = :id")
    fun getById(id: Int): LoaiMonAn

    @Insert
    fun insert(loaiMonAn: LoaiMonAn)

    @Update
    fun update(loaiMonAn: LoaiMonAn)

    @Delete
    fun delete(loaiMonAn: LoaiMonAn)
}

@Dao
interface MonAnDao {
    @Query("SELECT * FROM MonAn")
    fun getAll(): List<MonAn>

    @Query("SELECT * FROM MonAn WHERE id = :id")
    fun getById(id: Int): MonAn

    @Insert
    fun insert(monAn: MonAn)

    @Update
    fun update(monAn: MonAn)

    @Delete
    fun delete(monAn: MonAn)
}

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getById(id: Int): User

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}

@Dao
interface GioHangDao {
    @Query("SELECT * FROM GioHang")
    fun getAll(): List<GioHang>

    @Query("SELECT * FROM GioHang WHERE id = :id")
    fun getById(id: Int): GioHang

    @Insert
    fun insert(gioHang: GioHang)

    @Update
    fun update(gioHang: GioHang)

    @Delete
    fun delete(gioHang: GioHang)
}

@Dao
interface DonHangDao {
    @Query("SELECT * FROM DonHang")
    fun getAll(): List<DonHang>

    @Query("SELECT * FROM DonHang WHERE id = :id")
    fun getById(id: Int): DonHang

    @Insert
    fun insert(donHang: DonHang)

    @Update
    fun update(donHang: DonHang)

    @Delete
    fun delete(donHang: DonHang)
}

@Dao
interface ChiTietDonHangDao {
    @Query("SELECT * FROM ChiTietDonHang")
    fun getAll(): List<ChiTietDonHang>

    @Query("SELECT * FROM ChiTietDonHang WHERE id = :id")
    fun getById(id: Int): ChiTietDonHang

    @Insert
    fun insert(chiTietDonHang: ChiTietDonHang)

    @Update
    fun update(chiTietDonHang: ChiTietDonHang)

    @Delete
    fun delete(chiTietDonHang: ChiTietDonHang)
}

