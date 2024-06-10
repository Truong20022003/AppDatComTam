package com.example.appdatcomtam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import com.example.appdatcomtam.Database.DbHelper
import com.example.appdatcomtam.ViewModel.LoginViewModel
import com.example.appdatcomtam.Navigation.ScreenNav
import com.example.appdatcomtam.Repo.RepoCategory
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModel
import com.example.appdatcomtam.ViewModel.LoaiMonAnViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: LoaiMonAnViewModel
    private lateinit var repoCategory: RepoCategory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val db = DbHelper.getInstance(this)
            val userDao = db.userDao()

            repoCategory = RepoCategory(db)
            val categoryViewModel = remember {
                LoaiMonAnViewModel(repoCategory)
            }
            categoryViewModel.insertCate()
//            val factory = LoaiMonAnViewModelFactory(repoCategory)
//            viewModel = ViewModelProvider(this, factory).get(LoaiMonAnViewModel::class.java)
            val viewModel = remember { LoginViewModel(userDao) }
            viewModel.insertSampleAdminIfNeeded()

            ScreenNav()
        }
    }
}
