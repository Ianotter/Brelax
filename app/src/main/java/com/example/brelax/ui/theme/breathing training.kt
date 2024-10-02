package com.example.brelax.ui.theme

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.animation.doOnEnd
import com.example.brelax.R


class BreathingActivity : AppCompatActivity() {
    private lateinit var sun: ImageView
    private lateinit var cloudLeft: ImageView
    private lateinit var cloudRight: ImageView
    private lateinit var timer: TextView
    private lateinit var cycleCountText: TextView // 用於顯示循環次數
    private lateinit var breathingPhase: TextView

    private var cycleCount = 0 // 計算呼吸循環次數
    private var cycleDuration = 0L // 單個循環的總時間
    private var totalDuration = 0L // 總的持續時間
    private val breathingMethods = listOf("4-7-8", "3-3-3", "4-2-6", "Box")
    private var selectedMethod = "4-7-8"
    private var totalTime = 1 // 預設為 1 分鐘
    private var maxCycles = 0 // 儲存可循環的最大次數

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 接收從 BreathScreenbox 傳遞的呼吸方法和時間
        selectedMethod = intent.getStringExtra("BREATHING_METHOD") ?: "4-7-8"
        totalTime = intent.getIntExtra("TIME_MINUTES", 1)

        // 更新呼吸方法的持續時間
        updateCycleDuration(breathingMethods.indexOf(selectedMethod))
        totalDuration = totalTime * 60 * 1000L // 計算總時間 (轉換為毫秒)
        maxCycles = (totalDuration / cycleDuration).toInt() // 計算最多循環次數

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("選擇的呼吸方法: $selectedMethod", style = TextStyle(
                        fontSize = 20.sp,  // 字體大小
                        fontWeight = FontWeight.Bold,  // 字體粗細
                        color = Color.Black  // 字體顏色
                    )
                    )
                    Text("呼吸時間: $totalTime 分鐘", style = TextStyle(
                        fontSize = 20.sp,  // 字體大小
                        fontWeight = FontWeight.Bold,  // 字體粗細
                        color = Color.Black  // 字體顏色
                    ))

                    // 顯示最多循環次數
                    cycleCountText = TextView(this@BreathingActivity)
                    cycleCountText.text = "最多可循環 $maxCycles 次"
                    addContentView(cycleCountText, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))

                    StartButton(onClick = {
                        cycleCount = 0 // 重置循環次數
                        startBreathingCycle()
                    })
                }
            }
        }
    }

    @Composable
    fun TimeSelectionRow(onTimeSelected: (Int) -> Unit) {
        val items = (1..10).toList() // 數字列表
        val lazyListState = rememberLazyListState()
        val centralIndex = remember { mutableStateOf(1) } // 默認選中 1 分鐘

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                state = lazyListState,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 1.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(items.size) { index ->
                    val item = items[index]
                    val isSelected = centralIndex.value == item // 判斷當前是否是選中的數字

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (isSelected) Color.White else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                centralIndex.value = item // 點擊時設置選中項目
                                onTimeSelected(item) // 通知選中的時間
                            }
                    ) {
                        Text(
                            text = item.toString(),
                            fontSize = if (isSelected) 24.sp else 20.sp,
                            color = if (isSelected) Color(0xFF0E0F0F) else Color.Gray,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    private fun updateCycleDuration(methodIndex: Int) {
        cycleDuration = when (methodIndex) {
            0 -> 4_000L + 7_000L + 8_000L // 4吸-7屏-8吐
            1 -> 3_000L + 3_000L + 3_000L // 3吸-3屏-3吐
            2 -> 4_000L + 2_000L + 6_000L // 4吸-2屏-6吐
            3 -> 4_000L + 4_000L + 4_000L + 4_000L // 4吸-4屏-4吐-4屏
            else -> 0L
        }
    }

    private fun startBreathingCycle() {
        if (totalDuration <= 0) {
            breathingPhase.text = "結束"
            return
        }

        cycleCount += 1 // 計算完成的循環次數

        // 吸氣
        breathingPhase.text = "吸氣"
        animateClouds(close = true, duration = 4000)

        countdown(4) {
            // 屏氣
            breathingPhase.text = "屏氣"
            sun.setImageResource(R.drawable.calmsun) // 屏氣階段顯示冷靜太陽

            countdown(7) {
                // 吐氣
                breathingPhase.text = "吐氣"

                // 根據循環次數切換太陽顯示
                if (cycleCount == 1 || cycleCount == 2) {
                    sun.setImageResource(R.drawable.calmsun) // 第一次和第二次循環顯示冷靜太陽
                } else {
                    sun.setImageResource(R.drawable.happysun) // 其他循環顯示開心太陽
                }

                animateClouds(close = false, duration = 8000)

                countdown(8) {
                    // 循環下一個呼吸階段，並計算剩餘時間
                    totalDuration -= cycleDuration // 減去已經使用的時間
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
        var timeLeft = seconds
        timer.text = timeLeft.toString()

        val timerAnimator = ValueAnimator.ofInt(seconds, 0)
        timerAnimator.duration = seconds * 1000L
        timerAnimator.addUpdateListener { animation ->
            timeLeft = animation.animatedValue as Int
            timer.text = timeLeft.toString()
        }
        timerAnimator.start()
        timerAnimator.doOnEnd { onFinish() }
    }
}