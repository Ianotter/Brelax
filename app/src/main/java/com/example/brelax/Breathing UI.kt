package com.example.brelax

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.min
import com.example.brelax.ui.theme.BreathingDurationSelector
import com.example.brelax.ui.theme.BreathingMethodInfo
import com.example.brelax.ui.theme.BreathingMethodInfo2
import com.example.brelax.ui.theme.BreathingModeSelector
import com.example.brelax.ui.theme.Breathmethod1
import com.example.brelax.ui.theme.Breathmethod2
import com.example.brelax.ui.theme.Breathmethod3
import com.example.brelax.ui.theme.PreviousButton
import com.example.brelax.ui.theme.StartButton

@Preview
@Composable
fun BreathScreen478() {
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

        // Column 是一個縱向排列的佈局容器，允許其子元素從上到下排列
        Column(
            modifier = Modifier
                .fillMaxSize()  // 填滿整個螢幕
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,  // 子元素水平居中
            verticalArrangement = Arrangement.Top  // 子元素從上往下排列
        ) {
            PreviousButton(
                modifier = Modifier.align(Alignment.Start)
            )


            // Spacer 是一個佔位元素，這裡用來提供 40dp 的上方空間
            Spacer(modifier = Modifier.height(10.dp))

            // Image composable 用來顯示一個圖像 (太陽表情符號)
            Image(
                painter = painterResource(id = R.drawable.sun_choose),  // 設置圖像資源
                contentDescription = null,  // 不需要文字描述
                modifier = Modifier
                    .size(200.dp)  // 設置圖像大小為 150dp
            )

            // Spacer 提供 20dp 的垂直間距
            Spacer(modifier = Modifier.height(30.dp))

            // 呼吸模式選擇器 (快速放鬆, 深度放鬆, 專注放鬆)
            BreathingModeSelector()

            Spacer(modifier = Modifier.height(25.dp))

            Breathmethod1()

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸法介紹區塊
            BreathingMethodInfo(title = "4-7-8呼吸法",  // 更新標題
                shortD = "適合於壓力大或需要放鬆的時候使用",  // 更新簡單說明
                detailed= "深度放鬆呼吸法可以有效地幫助您釋放壓力，並促進睡眠。" +
                        "透過調整呼吸節奏，您能讓身體進入深度放鬆，" +
                        "特別適合在工作後或面對挑戰時使用，幫助您重新集中精力。",inhale = "吸氣 4 秒",
                exhale ="呼氣 7 秒" ,
                hold="屏氣 8 秒")

            Spacer(modifier = Modifier.height(25.dp))

            // 控制吸氣、屏氣、吐氣的秒數顯示
            Text(text = "呼吸分鐘", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸分鐘數的選擇器
            BreathingDurationSelector()

            Spacer(modifier = Modifier.height(25.dp))

            // "開始呼吸" 按鈕
            StartButton()
        }
    }
}


@Preview
@Composable
fun BreathScreen426() {
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

        // Column 是一個縱向排列的佈局容器，允許其子元素從上到下排列
        Column(
            modifier = Modifier
                .fillMaxSize()  // 填滿整個螢幕
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,  // 子元素水平居中
            verticalArrangement = Arrangement.Top  // 子元素從上往下排列
        ) {
            PreviousButton(
                modifier = Modifier.align(Alignment.Start)
            )
            // Spacer 是一個佔位元素，這裡用來提供 40dp 的上方空間
            Spacer(modifier = Modifier.height(20.dp))

            // Image composable 用來顯示一個圖像 (太陽表情符號)
            Image(
                painter = painterResource(id = R.drawable.sun_choose),  // 設置圖像資源
                contentDescription = null,  // 不需要文字描述
                modifier = Modifier
                    .size(200.dp)  // 設置圖像大小為 150dp
            )

            // Spacer 提供 20dp 的垂直間距
            Spacer(modifier = Modifier.height(30.dp))

            // 呼吸模式選擇器 (快速放鬆, 深度放鬆, 專注放鬆)
            BreathingModeSelector()

            Spacer(modifier = Modifier.height(25.dp))

            Breathmethod1()

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸法介紹區塊
            BreathingMethodInfo(title = "4-2-6呼吸法",  // 更新標題
                shortD = "適合在壓力大的情況下，幫助回到平靜狀態",  // 更新簡單說明
                detailed = "是幫助從交感神經系統（逃跑或戰鬥反應）轉移到副交感神經系統（休息和消化狀態）。" +
                        "這種方法可以促進橫膈膜呼吸，活化迷走神經，" +
                        "從而降低心跳速度、促進消化，幫助身體回到平靜狀態，減少壓力和焦慮。",
                inhale = "吸氣 4 秒",
                exhale ="呼氣 2 秒" ,
                hold="屏氣 6 秒")

            Spacer(modifier = Modifier.height(25.dp))

            // 控制吸氣、屏氣、吐氣的秒數顯示
            Text(text = "呼吸分鐘", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸分鐘數的選擇器
            BreathingDurationSelector()

            Spacer(modifier = Modifier.height(25.dp))

            // "開始呼吸" 按鈕
            StartButton()
        }
    }
}

@Preview
@Composable
fun BreathScreen333() {
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

        // Column 是一個縱向排列的佈局容器，允許其子元素從上到下排列
        Column(
            modifier = Modifier
                .fillMaxSize()  // 填滿整個螢幕
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,  // 子元素水平居中
            verticalArrangement = Arrangement.Top  // 子元素從上往下排列
        ) {
            PreviousButton(
                modifier = Modifier.align(Alignment.Start)
            )
            // Spacer 是一個佔位元素，這裡用來提供 40dp 的上方空間
            Spacer(modifier = Modifier.height(20.dp))

            // Image composable 用來顯示一個圖像 (太陽表情符號)
            Image(
                painter = painterResource(id = R.drawable.sun_choose),  // 設置圖像資源
                contentDescription = null,  // 不需要文字描述
                modifier = Modifier
                    .size(200.dp)  // 設置圖像大小為 150dp
            )

            // Spacer 提供 20dp 的垂直間距
            Spacer(modifier = Modifier.height(30.dp))

            // 呼吸模式選擇器 (快速放鬆, 深度放鬆, 專注放鬆)
            BreathingModeSelector()

            Spacer(modifier = Modifier.height(25.dp))

            Breathmethod2()

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸法介紹區塊
            BreathingMethodInfo(title = "3-3-3 呼吸法",  // 更新標題
                shortD = "適合日常短暫休息時應用",  // 更新簡單說明
                detailed = "3-3-3呼吸法是一種簡單而有效的呼吸技巧，幫助個人快速放鬆、減少壓力和焦慮感，" +
                        "有助於平靜神經系統的快速呼吸法，可以在日常生活中隨時隨地應用。" +
                        "當您感到焦慮或壓力並需要快速恢復平靜時，這是一個很好的工具。",
                inhale = "吸氣 3 秒",
                exhale ="呼氣 3 秒" ,
                hold="屏氣 3 秒")

            Spacer(modifier = Modifier.height(25.dp))

            // 控制吸氣、屏氣、吐氣的秒數顯示
            Text(text = "呼吸分鐘", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸分鐘數的選擇器
            BreathingDurationSelector()

            Spacer(modifier = Modifier.height(25.dp))

            // "開始呼吸" 按鈕
            StartButton()
        }
    }
}

@Preview
@Composable
fun BreathScreenbox() {
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

        // Column 是一個縱向排列的佈局容器，允許其子元素從上到下排列
        Column(
            modifier = Modifier
                .fillMaxSize()  // 填滿整個螢幕
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,  // 子元素水平居中
            verticalArrangement = Arrangement.Top  // 子元素從上往下排列
        ) {
            PreviousButton(
            modifier = Modifier.align(Alignment.Start)
        )
            // Spacer 是一個佔位元素，這裡用來提供 40dp 的上方空間
            Spacer(modifier = Modifier.height(20.dp))

            // Image composable 用來顯示一個圖像 (太陽表情符號)
            Image(
                painter = painterResource(id = R.drawable.sun_choose),  // 設置圖像資源
                contentDescription = null,  // 不需要文字描述
                modifier = Modifier
                    .size(200.dp)  // 設置圖像大小為 150dp
            )

            // Spacer 提供 20dp 的垂直間距
            Spacer(modifier = Modifier.height(30.dp))

            // 呼吸模式選擇器 (快速放鬆, 深度放鬆, 專注放鬆)
            BreathingModeSelector()

            Spacer(modifier = Modifier.height(25.dp))

            Breathmethod3()

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸法介紹區塊
            BreathingMethodInfo2(title = "Box 呼吸法",  // 更新標題
                shortD = "專注與減壓，特別適合在工作或需要提升專注力時使用",  // 更新簡單說明
                detailed = "Box breathing（方形呼吸），又稱為四方呼吸，是一種簡單的呼吸技巧，" +
                        "主要用於幫助減輕壓力、提升專注力和促進放鬆。" +
                        "它可以幫助調節自主神經系統，減少焦慮並改善心理狀態。",
                inhale = "吸氣 4 秒",
                exhale ="呼氣 4 秒" ,
                hold="屏氣 4 秒")

            Spacer(modifier = Modifier.height(25.dp))

            // 控制吸氣、屏氣、吐氣的秒數顯示
            Text(text = "呼吸分鐘", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(25.dp))

            // 呼吸分鐘數的選擇器
            BreathingDurationSelector()

            Spacer(modifier = Modifier.height(25.dp))

            // "開始呼吸" 按鈕
            StartButton()
        }
    }
}

