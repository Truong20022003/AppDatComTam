package com.example.appdatcomtam.Quanly.MonAn.Them

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.appdatcomtam.Model.LoaiMonAn
import com.example.appdatcomtam.Quanly.MonAn.copyUriToInternalStorage
import com.example.appdatcomtam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemMonAnScreen(viewModel: ThemMonAnViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            navController.popBackStack()
                            viewModel.imageUri = null
                            viewModel.gia = ""
                            viewModel.tenMonAn = ""
                            viewModel.selectedOptionText = "Món chính"
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                modifier = Modifier.size(20.dp),
                                tint = Color.White,
                                contentDescription = "back"
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.logoimages),
                            modifier = Modifier
                                .size(60.dp)
                                .padding(start = 8.dp),
                            contentDescription = "logo"
                        )
                        Text(
                            "Thêm món ăn",
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.cairo_bold))
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF252121)
                ),
                modifier = Modifier.shadow(
                    10.dp,
                    RoundedCornerShape(10.dp),
                    spotColor = Color.Black
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .background(Color(0xFF252121))
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                showThemMonAnScreen(viewModel, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showThemMonAnScreen(viewModel: ThemMonAnViewModel, navController: NavController) {
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.imageUri = it
            viewModel.imagePath = copyUriToInternalStorage(context, it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        viewModel.imagePath?.let {
            Image(
                painter = rememberImagePainter(data = it),
                contentDescription = "selected_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { imagePickerLauncher.launch("image/*") }
            )
        } ?: Image(
            painter = painterResource(id = R.drawable.them_hinh_anh),
            contentDescription = "hinh_anh",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { imagePickerLauncher.launch("image/*") }
        )

        ComboBoxExample(
            text = "Loại món",
            onOptionSelected = { selectedOption ->
                viewModel.selectedOptionText = selectedOption.tenLoaiMonAn!!
                viewModel.idloai = selectedOption.id.toString()
            },
            viewModel = viewModel,
            defaultOption = viewModel.loaiMonAns.value.firstOrNull()
        )


        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Giá",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 8.dp),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            TextField(
                value = viewModel.gia,
                onValueChange = { newValue ->
                    viewModel.gia = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Tên món ăn",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 8.dp),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold))
            )
            OutlinedTextField(
                value = viewModel.tenMonAn,
                onValueChange = { newValue ->
                    viewModel.tenMonAn = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }
        Button(
            onClick = {
                viewModel.onClickAdd(context)
                if (viewModel.check) navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(top = 50.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFB703)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Thêm", fontSize = 20.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxExample(
    text: String,
    onOptionSelected: (LoaiMonAn) -> Unit,
    viewModel: ThemMonAnViewModel,
    defaultOption: LoaiMonAn? = null
) {
    var expanded by remember { mutableStateOf(false) }
    val loaiMons by viewModel.loaiMonAns.collectAsState()

    var selectedText by remember {
        mutableStateOf(defaultOption?.tenLoaiMonAn ?: viewModel.selectedOptionText)
    }

    Column {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 8.dp),
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.cairo_bold))
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { /* Do nothing */ },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .clickable { expanded = true },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (loaiMons.isNotEmpty()) {
                    loaiMons.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedText = selectionOption.tenLoaiMonAn!!
                                viewModel.selectedOptionText = selectionOption.tenLoaiMonAn!!
                                viewModel.idloai = selectionOption.id.toString()
                                onOptionSelected(selectionOption)
                                expanded = false
                            },
                            text = { Text(selectionOption.tenLoaiMonAn!!) }
                        )
                    }
                }
            }
        }
    }
}
