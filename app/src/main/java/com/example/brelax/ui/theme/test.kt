package com.example.brelax.ui.theme
import android.accessibilityservice.AccessibilityService.ScreenshotResult
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.regex.Pattern
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.min
import com.example.brelax.R
import com.example.brelax.ui.theme.BreathingDurationSelector
import com.example.brelax.ui.theme.BreathingMethodInfo
import com.example.brelax.ui.theme.BreathingMethodInfo2
import com.example.brelax.ui.theme.BreathingModeSelector
import com.example.brelax.ui.theme.Breathmethod1
import com.example.brelax.ui.theme.Breathmethod2
import com.example.brelax.ui.theme.Breathmethod3
import com.example.brelax.ui.theme.PreviousButton
import com.example.brelax.ui.theme.StartButton
import kotlin.math.log

@Preview
@Composable
fun BreathScreen8() {
    // 使用 remember 來保存當前選中的模式，並初始化為"深度放鬆"
    val selectedMode = remember { mutableStateOf("深度放鬆") }

    // 保存選擇的呼吸時間
    val breathingTime = remember { mutableStateOf(4f) }  // 初始化為 4 分鐘

    // 使用 Box 來疊加背景圖片和其他內容
    Box(
        modifier = Modifier.fillMaxSize()  // 填滿整個螢幕
    ) {
        // 背景圖片
        Image(
            painter = painterResource(id = R.drawable.happybackground),  // 設置背景圖片資源
            contentDescription = null,  // 不需要文字描述
            modifier = Modifier.fillMaxSize(),  // 背景圖片填滿整個螢幕
            contentScale = ContentScale.Crop  // 確保圖片填滿並裁剪
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            PreviousButton(modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(10.dp))

            // Image composable 用來顯示一個圖像 (太陽表情符號)
            Image(
                painter = painterResource(id = R.drawable.sun_choose),  // 設置圖像資源
                contentDescription = null,  // 不需要文字描述
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 呼吸模式選擇器
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(listOf("快速放鬆", "深度放鬆", "專注放鬆")) { mode ->
                    TextButton(
                        onClick = {
                            selectedMode.value = mode  // 點擊時更新選中的模式
                        },
                        modifier = Modifier
                            .background(if (selectedMode.value == mode) Color.Blue else Color.LightGray)
                            .padding(8.dp)
                    ) {
                        Text(text = mode, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // 根據選擇的模式來顯示對應的呼吸方式
            when (selectedMode.value) {
                "深度放鬆" -> {
                    BreathingMethodInfo3(
                        title = "4-7-8呼吸法",
                        shortD = "適合於壓力大或需要放鬆的時候使用",
                        detailed = "深度放鬆呼吸法可以有效地幫助您釋放壓力，並促進睡眠。",
                        inhale = "吸氣 4 秒",
                        exhale = "呼氣 7 秒",
                        hold = "屏氣 8 秒"
                    )
                }
                "快速放鬆" -> {
                    BreathingMethodInfo3(
                        title = "3-3-3呼吸法",
                        shortD = "快速恢復專注的呼吸法",
                        detailed = "快速放鬆呼吸法可以幫助您快速進入專注狀態，適合短暫的休息時間使用。",
                        inhale = "吸氣 3 秒",
                        exhale = "呼氣 3 秒",
                        hold = "屏氣 3 秒"
                    )
                }
                "專注放鬆" -> {
                    BreathingMethodInfo3(
                        title = "4-2-6呼吸法",
                        shortD = "專注於某個目標的呼吸法",
                        detailed = "專注放鬆呼吸法能幫助您提升注意力，適合在需要長時間專注的情況下使用。",
                        inhale = "吸氣 4 秒",
                        exhale = "呼氣 6 秒",
                        hold = "屏氣 2 秒"
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                items((1..10).toList()) { time ->
                    TextButton(
                        onClick = { breathingTime.value = time.toFloat() },  // 更新選中的分鐘數
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                if (breathingTime.value.toInt() == time) Color.Blue else Color.LightGray,
                                shape = RoundedCornerShape(50)
                            )
                            .size(50.dp),  // 設置每個數字的大小
                    ) {
                        Text(
                            text = "$time",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // 顯示當前選中的分鐘數
            Text(text = "選擇的時間：${breathingTime.value} 分鐘", fontSize = 18.sp)


            Spacer(modifier = Modifier.height(25.dp))

            // "Go Breathing" 按鈕
            StartButton()
        }
    }
}

@Composable
fun BreathingMethodInfo3(title: String, shortD: String, detailed: String, inhale: String, exhale: String, hold: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = shortD, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = detailed, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = inhale)
                Text(text = hold)
                Text(text = exhale)
            }
        }
    }
}




