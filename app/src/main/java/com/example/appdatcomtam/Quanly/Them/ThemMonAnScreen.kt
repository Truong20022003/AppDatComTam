package com.example.appdatcomtam.Quanly.Them

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.appdatcomtam.Quanly.LoaiMonAn
import com.example.appdatcomtam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemMonAnScreen(viewModel: ThemMonAnViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { if (context is ThemMonAnActivity) context.finish() }) {
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
            ) {
                showThemMonAnScreen(viewModel)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showThemMonAnScreen(viewModel: ThemMonAnViewModel) {
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.imageUri = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        viewModel.imageUri?.let {
            Image(
                painter = rememberImagePainter(data = it),
                contentDescription = "selected_image",
                modifier = Modifier
                    .size(200.dp)
                    .clickable { imagePickerLauncher.launch("image/*") }
            )
        } ?: Image(
            painter = painterResource(id = R.drawable.them_hinh_anh),
            contentDescription = "hinh_anh",
            modifier = Modifier
                .size(200.dp)
                .clickable { imagePickerLauncher.launch("image/*") }
        )
        ComboBoxExample(
            text = "Loại món",
            onOptionSelected = { newSelectedOption ->
                viewModel.selectedOptionText = newSelectedOption
            },
            viewModel = viewModel // Truyền viewModel vào
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
            onClick = {  viewModel.onClickAdd(context) },
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
    onOptionSelected: (String) -> Unit,
    viewModel: ThemMonAnViewModel // Truyền viewModel vào
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf(
        LoaiMonAn("Sườn"),
        LoaiMonAn("Bì cả"),
        LoaiMonAn("Trứng chả"),
        LoaiMonAn("Sườn chả")
    )

    // Thiết lập giá trị mặc định nếu chưa có giá trị nào được chọn
    if (viewModel.selectedOptionText.isEmpty()) {
        viewModel.selectedOptionText = options.first().tenLoaiMonAn
    }

    var selectedText by remember { mutableStateOf(viewModel.selectedOptionText) }

    Column {
        Text(
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 8.dp),
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
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText = selectionOption.tenLoaiMonAn
                            viewModel.selectedOptionText = selectionOption.tenLoaiMonAn
                            onOptionSelected(selectionOption.tenLoaiMonAn)
                            expanded = false
                        },
                        text = { Text(selectionOption.tenLoaiMonAn) }
                    )
                }
            }
        }
    }
}
