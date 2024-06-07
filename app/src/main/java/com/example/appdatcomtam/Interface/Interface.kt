package com.example.appdatcomtam.Interface

import androidx.room.*
import com.example.appdatcomtam.Model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LoaiMonAnDao {

    @Insert
    suspend fun insert(loaiMonAn: LoaiMonAn)

    @Query("SELECT * FROM LoaiMonAn")
    fun getAll(): Flow<List<LoaiMonAn>>

    @Query("SELECT * FROM LoaiMonAn WHERE id = :id")
    fun getById(id: Int): Flow<LoaiMonAn>

    @Update
    suspend fun update(loaiMonAn: LoaiMonAn)

    @Delete
    suspend fun delete(loaiMonAn: LoaiMonAn)
}

@Dao
interface MonAnDao {

    @Insert
    suspend fun insert(monAn: MonAn)

    @Query("SELECT * FROM MonAn")
    fun getAll(): Flow<List<MonAn>>

    @Query("SELECT * FROM MonAn WHERE id = :id")
    fun getById(id: Int): Flow<MonAn>

    @Update
    suspend fun update(monAn: MonAn)

    @Delete
    suspend fun delete(monAn: MonAn)
}

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getById(id: Int): Flow<User>

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}

@Dao
interface GioHangDao {

    @Insert
    suspend fun insert(gioHang: GioHang)

    @Query("SELECT * FROM GioHang")
    fun getAll(): Flow<List<GioHang>>

    @Query("SELECT * FROM GioHang WHERE id = :id")
    fun getById(id: Int): Flow<GioHang>

    @Update
    suspend fun update(gioHang: GioHang)

    @Delete
    suspend fun delete(gioHang: GioHang)
}

@Dao
interface DonHangDao {

    @Insert
    suspend fun insert(donHang: DonHang)

    @Query("SELECT * FROM DonHang")
    fun getAll(): Flow<List<DonHang>>

    @Query("SELECT * FROM DonHang WHERE id = :id")
    fun getById(id: Int): Flow<DonHang>

    @Update
    suspend fun update(donHang: DonHang)

    @Delete
    suspend fun delete(donHang: DonHang)
}

@Dao
interface ChiTietDonHangDao {

    @Insert
    suspend fun insert(chiTietDonHang: ChiTietDonHang)

    @Query("SELECT * FROM ChiTietDonHang")
    fun getAll(): Flow<List<ChiTietDonHang>>

    @Query("SELECT * FROM ChiTietDonHang WHERE id = :id")
    fun getById(id: Int): Flow<ChiTietDonHang>

    @Update
    suspend fun update(chiTietDonHang: ChiTietDonHang)

    @Delete
    suspend fun delete(chiTietDonHang: ChiTietDonHang)
}