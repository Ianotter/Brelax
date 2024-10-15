package com.example.brelax.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.brelax.R


@Composable
fun Nirsbluetooth(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.nirsbackground),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            // NIRS測量中的標題
            Text(
                text = "選擇測量時間",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.tblack),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            // 顯示雲朵圖像
            Image(
                painter = painterResource(id = R.drawable.nirscloud), // 替換成你的雲圖
                contentDescription = "雲朵圖像",
                modifier = Modifier
                    .size(230.dp)
                    .padding(vertical = 20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.bluetooth),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))
            bluetoothbutton()

            Spacer(modifier = Modifier.height(80.dp))
            // 開始測量按鈕
            Button1(
                onClick = {
                    // 開始測量邏輯，導航到測量頁面
                    navController.navigate("nirsmeasurement")
                },
                text = "開始測量",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previewbluetooh() {
    // 在預覽中，忽略 navController
    Nirsbluetooth(navController = rememberNavController())
}