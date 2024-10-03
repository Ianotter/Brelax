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



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(80.dp))
        // 頂部導航欄
        TopNavBar()

        Spacer(modifier = Modifier.height(150.dp))

        // 太陽與雲朵圖片
        Image(
            painter = painterResource(id = R.drawable.happysun),  // 確保此圖片存在於res/drawable資料夾中
            contentDescription = "Sun and Cloud",
            modifier = Modifier.size(200.dp)  // 調整圖片大小
        )
        Spacer(modifier = Modifier.height(150.dp))
        // Go Breathing 按鈕
        Button1(
            modifier = Modifier
                .padding(10.dp)
                .width(250.dp),
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
            .clip(RoundedCornerShape(50.dp))
            .width(280.dp)
            .aspectRatio(4f)
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) { iconbutton(
            painter = painterResource(id = R.drawable.bolticon), // 使用你的圖標資源
            text  = "tES",
        )
        iconbutton(
            painter = painterResource(id = R.drawable.breathicon), // 使用你的圖標資源
            text  = "Breath",
        )
        iconbutton(
            painter = painterResource(id = R.drawable.nirsicon), // 使用你的圖標資源
            text  = "NIRS",
        )
        iconbutton(
            painter = painterResource(id = R.drawable.usericon), // 使用你的圖標資源
            text  = "USER",
        )
    }
}

@Preview
@Composable
fun iconbutton(
    modifier: Modifier = Modifier,
    text: String = "x",
    painter: Painter = painterResource(id = R.drawable.ic_missing_image),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(60.dp) // 圓形按鈕的大小
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center // 將內容置中
    ) {
        // 內部的 Column 用於圖片和文字的佈局
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // 水平置中
            verticalArrangement = Arrangement.Center // 垂直置中
        ) {
            // 圖片
            Image(
                painter = painter,
                contentDescription = null, // 可選，給圖片添加描述
                modifier = Modifier.size(30.dp) // 圖片的大小
            )
            Spacer(modifier = modifier.height(3.dp))
            // 文字
            Text(
                text = text,
                modifier = Modifier.padding(top = 0.dp), // 文字上邊距
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
                color = Color(0xFF585858) // 文字顏色
            )
        }
    }
}





