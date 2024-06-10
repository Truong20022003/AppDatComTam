package com.example.appdatcomtam.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatcomtam.Interface.UserDao
import com.example.appdatcomtam.Model.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UserDao) : ViewModel() {

    private val _isAuthenticated = MutableLiveData<Boolean?>()
    val isAuthenticated: LiveData<Boolean?> = _isAuthenticated

    fun insertSampleAdminIfNeeded() {
        viewModelScope.launch {
            val users = dao.getAll()
            users.collect {
                if (it.isEmpty()) {
                    dao.insert(User(tenDangNhap = "admin1", matKhau = "123", diaChi = "ha noi"))
                    dao.insert(User(tenDangNhap = "admin2", matKhau = "456", diaChi = "nghe an"))
                }
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = dao.getByUserNamePassword(username, password)
            user.collect {
                _isAuthenticated.value = it != null
            }
        }
    }
}
