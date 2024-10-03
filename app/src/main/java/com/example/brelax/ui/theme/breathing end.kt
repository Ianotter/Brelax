package com.example.brelax.ui.theme

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brelax.R
import com.google.android.ads.mediationtestsuite.activities.HomeActivity

@Preview
@Composable
fun BreathScreenUI() {
    val context = LocalContext.current
    val selectedBreathingMethod = remember { mutableStateOf("4-7-8") }

    // 使用 Box 來疊加背景圖片和其他 UI 元素
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 背景圖
        Image(
            painter = painterResource(id = R.drawable.happybackground),  // 設置背景圖片資源
            contentDescription = null,  // 不需要文字描述
            modifier = Modifier.fillMaxSize(),  // 背景圖片填滿整個螢幕
            contentScale = ContentScale.Crop  // 確保圖片填滿並裁剪
        )

        // UI 元素
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // 返回按鈕
            PreviousButton(
                modifier = Modifier.align(Alignment.Start)
            )

            Image(
                painter = painterResource(id = R.drawable.calmcloud),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.End)
            )

            Row(modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .width(200.dp)
                .background(Color(0xFFFFFFFF))
                .aspectRatio(2.5f)
            ){ Box(
                modifier = Modifier.fillMaxSize(), // 填滿整個 Row
                contentAlignment = Alignment.Center // 內容置中
            ) {
                Text(
                    text = "完成訓練啦！\n心情感覺如何",
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    color = colorResource(id = R.color.tblack),
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center, // 文字水平居中
                 fontWeight = FontWeight.ExtraBold,
                )
            }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 太陽圖示
            Image(
                painter = painterResource(id = R.drawable.lightsun),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 提示訊息


            Spacer(modifier = Modifier.height(30.dp))

            // "再一次" 按鈕
            Button1(
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
                text = "再一次"
            )

            // "回首頁" 按鈕
            Button1(
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
                text = "回到首頁"
            )
        }
    }
}