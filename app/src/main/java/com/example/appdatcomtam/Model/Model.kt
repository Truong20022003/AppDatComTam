package com.example.appdatcomtam.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoaiMonAn(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val tenLoaiMonAn: String
)

@Entity
data class MonAn(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idLoaiMonAn: Int,
    val gia: Double,
    val tenMonAn: String
)

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val tenDangNhap: String,
    val matKhau: String,
    val diaChi: String
)

@Entity
data class GioHang(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idUser: Int,
    val idMonAn: Int,
    val soLuong: Int
)

@Entity
data class DonHang(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idUser: Int,
    val ngayDatHang: String,
    val tongTien: Double,
    val trangThai: String
)

@Entity
data class ChiTietDonHang(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val maDonHang: Int,
    val maSanPham: Int,
    val soLuong: Int,
    val donGia: Double,
    val thanhTien: Double
)