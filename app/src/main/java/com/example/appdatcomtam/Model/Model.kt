package com.example.appdatcomtam.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LoaiMonAn(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "tenLoaiMonAn") var tenLoaiMonAn: String?,

)


//@Entity
//data class LoaiMonAn(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val tenLoaiMonAn: String
//)
//

@Entity
data class MonAn(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "idLoaiMonAn") var idLoaiMonAn: String?,
    @ColumnInfo(name = "gia") var gia: Double ,
    @ColumnInfo(name = "tenMonAn") var tenMonAn: String?,
    )
//@Entity
//data class MonAn(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val idLoaiMonAn: Int,
//    val gia: Double,
//    val tenMonAn: String
//)
//

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "tenDangNhap") var tenDangNhap: String?,
    @ColumnInfo(name = "matKhau") var matKhau: String?,
    @ColumnInfo(name = "diaChi") var diaChi: String?,
)
//@Entity
//data class User(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val tenDangNhap: String,
//    val matKhau: String,
//    val diaChi: String
//)
//
@Entity
data class GioHang(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "idUser") var idUser: Int ,
    @ColumnInfo(name = "idMonAn") var idMonAn: Int ,
    @ColumnInfo(name = "soLuong") var soLuong: Int ,

    )

//@Entity
//data class GioHang(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val idUser: Int,
//    val idMonAn: Int,
//    val soLuong: Int
//)
//

@Entity
data class DonHang(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "idUser") var idUser: Int ,
    @ColumnInfo(name = "ngayDatHang") var ngayDatHang: String?,
    @ColumnInfo(name = "tongTien") var tongTien: Double ,
    @ColumnInfo(name = "trangThai") var trangThai: String?,
)
//@Entity
//data class DonHang(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val idUser: Int,
//    val ngayDatHang: String,
//    val tongTien: Double,
//    val trangThai: String
//)
//

@Entity
data class ChiTietDonHang(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "maDonHang") var maDonHang: Int ,
    @ColumnInfo(name = "maSanPham") var maSanPham: Int ,
    @ColumnInfo(name = "soLuong") var soLuong: Int ,
    @ColumnInfo(name = "donGia") var donGia: Double ,
    @ColumnInfo(name = "thanhTien") var thanhTien: Double ,
)
//@Entity
//data class ChiTietDonHang(
//    @PrimaryKey(autoGenerate = true) val id: Int,
//    val maDonHang: Int,
//    val maSanPham: Int,
//    val soLuong: Int,
//    val donGia: Double,
//    val thanhTien: Double
//)