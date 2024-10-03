package com.example.brelax.ui.theme

import android.widget.ImageView
import androidx.core.animation.doOnEnd
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brelax.R

class BreathingActivity : ComponentActivity() {
    private lateinit var cloudLeft: ImageView
    private lateinit var cloudRight: ImageView
    private lateinit var sun: ImageView

    private var cycleCount by mutableStateOf(0) // 計算呼吸循環次數
    private var cycleDuration by mutableStateOf(0L) // 單個循環的總時間
    private var totalDuration by mutableStateOf(0L) // 總的持續時間
    private var breathingPhase by mutableStateOf("吸氣") // 當前呼吸階段
    private var timerValue by mutableStateOf(0) // 倒數計時的值
    private var selectedMethod = "4-7-8"
    private var totalTime = 1 // 預設為 1 分鐘
    private var maxCycles = 0 // 儲存可循環的最大次數


    data class BreathingMethod(val inhale: Int, val hold: Int, val exhale: Int)

    // 不同呼吸方法的定義
    private val breathingMethods = mapOf(
        "4-7-8" to BreathingMethod(inhale = 4, hold = 7, exhale = 8),
        "3-3-3" to BreathingMethod(inhale = 3, hold = 3, exhale = 3),
        "4-2-6" to BreathingMethod(inhale = 4, hold = 2, exhale = 6),
        "Box" to BreathingMethod(inhale = 4, hold = 4, exhale = 4, )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 接收從 BreathScreenbox 傳遞的呼吸方法和時間
        selectedMethod = intent.getStringExtra("BREATHING_METHOD") ?: "4-7-8"
        totalTime = intent.getIntExtra("TIME_MINUTES", 1)

        // 更新呼吸方法的持續時間
        // 根據選定的方法取得對應的呼吸方法物件
        val selectedBreathingMethod = breathingMethods[selectedMethod]
        if (selectedBreathingMethod != null) {
            // 更新呼吸方法的持續時間
            updateCycleDuration(selectedBreathingMethod)
        } else {
            // 預設使用 "4-7-8" 方法，如果 selectedMethod 無效
            updateCycleDuration(breathingMethods["4-7-8"]!!)
        }

        totalDuration = totalTime * 60 * 1000L // 計算總時間 (轉換為毫秒)
        maxCycles = (totalDuration / cycleDuration).toInt() // 計算最多循環次數

        setContent {
            MaterialTheme {
                BreathingScreen()
            }
        }
    }

    @Preview
    @Composable
    fun BreathingScreen() {
        Box (modifier = Modifier.fillMaxSize()// 填滿整個螢幕
        ){// 背景圖片
            Image(
                painter = painterResource(id = R.drawable.rainy_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Box(modifier = Modifier.fillMaxSize()) {
                // 背景圖片
                Image(
                    painter = painterResource(id = R.drawable.rainy_background),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                // 太陽圖片
                Image(
                    painter = painterResource(id = R.drawable.badsun),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center) // 將太陽放置在頂部中央
                )

                // 左側雲朵
                Image(
                    painter = painterResource(id = R.drawable.cloudleft),
                    contentDescription = null,
                    modifier = Modifier
                        .size(700.dp)
                        .align(Alignment.BottomStart)
                        .offset(x = -90.dp)
                )

                // 右側雲朵
                Image(
                    painter = painterResource(id = R.drawable.cloudleft),
                    contentDescription = null,
                    modifier = Modifier
                        .size(700.dp)
                        .align(Alignment.BottomEnd)
                        .graphicsLayer(rotationY = 180f) // 翻轉
                        .offset(x = -90.dp)
                )


                // 呼吸階段提示
                Text(
                    text = breathingPhase,
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 50.dp)
                        .align(Alignment.TopCenter)
                )

                // 倒數計時
                Text(
                    text = timerValue.toString(),
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 120.dp)
                        .align(Alignment.TopCenter)
                )

                // 開始按鈕
                // StartButton(onClick = { startBreathingCycle() })
            }
        }
    }




    @Composable
    fun Startbutton(onClick: () -> Unit) {
        Text(
            text = "開始",
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = onClick)
                .background(Color.Blue, shape = CircleShape)
                .padding(16.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }

    private fun updateCycleDuration(method: BreathingMethod) {
        // 計算單個循環的持續時間
        cycleDuration = (method.inhale + method.hold + method.exhale) * 1000L
    }

    private fun startBreathingCycle() {
        if (totalDuration <= 0) {
            breathingPhase = "結束"
            return
        }

        val method = breathingMethods[selectedMethod] ?: return
        cycleCount += 1

        // 吸氣階段
        breathingPhase = "吸氣"
        animateClouds(close = true, duration = method.inhale * 1000L)

        countdown(method.inhale) {
            // 屏氣階段
            breathingPhase = "屏氣"
            sun.setImageResource(R.drawable.calmsun)

            countdown(method.hold) {
                // 吐氣階段
                breathingPhase = "吐氣"
                if (cycleCount == 1 || cycleCount == 2) {
                    sun.setImageResource(R.drawable.calmsun)
                } else {
                    sun.setImageResource(R.drawable.happysun)
                }

                animateClouds(close = false, duration = method.exhale * 1000L)

                countdown(method.exhale) {
                    // 完成一個呼吸循環，減去使用的時間，並啟動下一個循環
                    totalDuration -= cycleDuration
                    startBreathingCycle()
                }
            }
        }
    }


    private fun animateClouds(close: Boolean, duration: Long) {
        val startLeft = if (close) 0 else -200  // 雲朵初始位置
        val endLeft = if (close) -200 else 0    // 雲朵最終位置

        val animatorLeft = ValueAnimator.ofFloat(startLeft.toFloat(), endLeft.toFloat())
        animatorLeft.duration = duration
        animatorLeft.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            cloudLeft.translationX = value
            cloudRight.translationX = -value  // 右側雲朵的移動方向相反
        }
        animatorLeft.start()
    }

    private fun countdown(seconds: Int, onFinish: () -> Unit) {
        var timePassed = 0
        timerValue = timePassed

        val timerAnimator = ValueAnimator.ofInt(0, seconds)
        timerAnimator.duration = seconds * 1000L
        timerAnimator.addUpdateListener { animation ->
            timePassed = animation.animatedValue as Int
            timerValue = timePassed + 1 // 顯示從1到指定秒數的數字
        }
        timerAnimator.start()
        timerAnimator.doOnEnd { onFinish() }
    }

}




