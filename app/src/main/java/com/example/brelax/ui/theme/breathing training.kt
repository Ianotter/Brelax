package com.example.brelax.ui.theme

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.brelax.R

class BreathingActivity : AppCompatActivity() {
    private lateinit var sun: ImageView
    private lateinit var cloudLeft: ImageView
    private lateinit var cloudRight: ImageView
    private lateinit var background: ImageView
    private lateinit var breathingPhase: TextView
    private lateinit var timer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breathing)

        sun = findViewById(R.id.sun)
        cloudLeft = findViewById(R.id.cloud_left)
        cloudRight = findViewById(R.id.cloud_right)
        background = findViewById(R.id.background)
        breathingPhase = findViewById(R.id.breathing_phase)
        timer = findViewById(R.id.timer)

        startBreathingCycle()
    }

    private fun startBreathingCycle() {
        // 吸氣 4 秒
        breathingPhase.text = "吸氣"
        animateClouds(close = true, duration = 4000)

        // 4 秒計時
        countdown(4) {
            // 屏氣 7 秒
            breathingPhase.text = "屏氣"
            sun.setImageResource(R.drawable.calmsun)  // 替換太陽表情
            countdown(7) {
                // 吐氣 8 秒
                breathingPhase.text = "吐氣"
                animateClouds(close = false, duration = 8000)

                countdown(8) {
                    // 循環下一個呼吸階段
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

