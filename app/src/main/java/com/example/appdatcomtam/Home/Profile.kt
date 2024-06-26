package com.example.appdatcomtam.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdatcomtam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logoimages),
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 8.dp),
                            contentDescription = "logo"
                        )
                        Text(
                            "Cum tứm đim",
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.cairo_bold))
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF252121)
                ),
                modifier = Modifier.shadow(
                    30.dp,
                    spotColor = Color.Black
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .background(Color(0xFF252121))
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                showProfile()
            }
        }
    )
}

private data class DataProfile(val img: Int, val text: String)

@Composable
fun showProfile() {
    val dulieu = listOf(
        DataProfile(R.drawable.zaloimages, "0947289641"),
        DataProfile(R.drawable.gmailimages, "doandtph42307@fpt.edu.vn"),
        DataProfile(R.drawable.phonesimages, "0936789241")
    )
    dulieu.forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = it.img),
                contentDescription = "img",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .then(
                        if (it.img == R.drawable.zaloimages) {
                            Modifier.clip(RoundedCornerShape(24.dp))
                        } else {
                            Modifier
                        }
                    )
            )
            Text(
                text = it.text,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 10.dp),
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.cairo_regular))
            )
        }
    }
}