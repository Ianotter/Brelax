package com.example.breathapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brelax.ui.theme.Button1
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.foundation.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.painter.Painter
import com.example.brelax.R

@Preview
@Composable
fun MainScreen() {
    Box(modifier = Modifier
        .fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.happybackground),  // 設置背景圖片資源
            contentDescription = null,  // 不需要文字描述
            modifier = Modifier.fillMaxSize(),  // 背景圖片填滿整個螢幕
            contentScale = ContentScale.Crop  // 確保圖片填滿並裁剪
        )
    // 使用Column來垂直排列元素
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),  // 設定背景顏色
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 頂部導航欄
        TopNavBar()

        // 太陽與雲朵圖片
        Image(
            painter = painterResource(id = R.drawable.happysun),  // 確保此圖片存在於res/drawable資料夾中
            contentDescription = "Sun and Cloud",
            modifier = Modifier.size(150.dp)  // 調整圖片大小
        )

        // Go Breathing 按鈕
        Button1(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.7f),
            text = "Go Breathing"
        )
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopNavBar() {
    // 使用Row來水平排列按鈕
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.bolticon), // 使用你的圖標資源
            contentDescription = "IES",
            modifier = Modifier.size(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.breath_s),
            contentDescription = "Breath",
            modifier =  Modifier.size(50.dp)
        )
        Image(painter = painterResource(id=R.drawable.nirsicon),
            contentDescription = "NIRS",
            modifier = Modifier.size(50.dp)
        )
        Image(painter = painterResource(id=R.drawable.usericon),
            contentDescription = "NIRS",
            modifier = Modifier.size(50.dp)
        )
    }
}




