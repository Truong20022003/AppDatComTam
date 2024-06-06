package com.example.appdatcomtam.Category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appdatcomtam.R

class CategoryManager {

}

@Preview
@Composable
private fun ChonChucNang(navCtrl: NavController? = null){
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .fillMaxWidth()
    ) {

        Button(onClick = { navCtrl?.navigate("add_food_type") }) {
            Image(painter = painterResource(R.drawable.logoimages),
                contentDescription = null,
                modifier = Modifier.size(20.dp))
            Text("Thêm loại món ăn")
        }
        Button(onClick = { navCtrl?.navigate("edit_food_type") }) {
            Image(painter = painterResource(R.drawable.logoimages),
                contentDescription = null,
                modifier = Modifier.size(20.dp))
            Text("Sửa loại món ăn")
        }
        Button(onClick = { navCtrl?.navigate("delete_food_type") }) {
            Image(painter = painterResource(R.drawable.logoimages),
                contentDescription = null,
                modifier = Modifier.size(20.dp))
            Text("Xoá loại món ăn")
        }

    }
}