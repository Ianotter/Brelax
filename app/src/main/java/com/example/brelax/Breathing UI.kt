package com.example.brelax.ui.theme
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brelax.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreathScreenbox(navigateToBreathingActivity: (String, Int) -> Unit) {
    val selectedMode = remember { mutableStateOf("深度放鬆") }
    val selectedBreathingMethod = remember { mutableStateOf("4-7-8") }
    val items = (1..10).toList() // 模擬的數據
    val lazyListState = rememberLazyListState() // LazyRow 的滾動狀態
    val centralIndex = remember { mutableStateOf(1) } // 預設選中索引
    val context = LocalContext.current
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

        // Column 是一個縱向排列的佈局容器
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
            Spacer(modifier = Modifier.height(20.dp))

            // 圖像顯示
            Image(
                painter = painterResource(id = R.drawable.sun_choose),  // 設置圖像資源
                contentDescription = null,  // 不需要文字描述
                modifier = Modifier
                    .size(200.dp)  // 設置圖像大小
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 呼吸模式選擇器
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // 呼吸模式選擇器
                BreathingModeSelector(selectedMode.value) { mode ->
                    selectedMode.value = mode
                    selectedBreathingMethod.value = "4-7-8" // 每次選擇模式時重置呼吸法選擇
                }
                Spacer(modifier = Modifier.height(16.dp))

                // 呼吸法選擇器
                Breathmethod1(selectedMode.value, selectedBreathingMethod.value) { method ->
                    selectedBreathingMethod.value = method
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 顯示選擇的呼吸法的詳細信息
                when (selectedBreathingMethod.value) {
                    "4-7-8" -> {
                        BreathingMethodInfo(
                            title = "4-7-8 呼吸法",
                            shortD = "適合在感到焦慮、無法入睡時使用",
                            detailed = "4-7-8呼吸法是一種能有效減少焦慮並改善睡眠的技巧。" +
                                    "這種方法有助於放慢呼吸速度，" +
                                    "促進身體進入深度放鬆的狀態。" +
                                    "當你難以入睡或面臨極大壓力與焦慮時，這項技巧特別有幫助。",
                            inhale = "吸氣 4 秒",
                            exhale = "呼氣 7 秒",
                            hold = "屏氣 8 秒"
                        )
                    }

                    "4-2-6" -> {
                        BreathingMethodInfo(
                            title = "4-2-6 呼吸法",
                            shortD = "適合在壓力大的情況下，幫助回到平靜狀態",
                            detailed = "是幫助從交感神經系統（逃跑或戰鬥反應）轉移到副交感神經系統（休息和消化狀態）。" +
                                    "這種方法可以促進橫膈膜呼吸，活化迷走神經，" +
                                    "從而降低心跳速度、促進消化，幫助身體回到平靜狀態，減少壓力和焦慮。",
                            inhale = "吸氣 4 秒",
                            exhale = "呼氣 6 秒",
                            hold = "屏氣 2 秒"
                        )
                    }
                    "3-3-3" -> {
                        BreathingMethodInfo(
                            title = "3-3-3 呼吸法",
                            shortD = "適合日常短暫休息時應用",
                            detailed = "3-3-3呼吸法是一種簡單而有效的呼吸技巧，幫助個人快速放鬆、減少壓力和焦慮感，" +
                                    "有助於平靜神經系統的快速呼吸法，可以在日常生活中隨時隨地應用。" +
                                    "當您感到焦慮或壓力並需要快速恢復平靜時，這是一個很好的工具。",
                            inhale = "吸氣 3 秒",
                            exhale = "呼氣 3 秒",
                            hold = "屏氣 3 秒"
                        )
                    }
                    "Box" -> {
                        BreathingMethodInfo(
                            title = "Box 呼吸法",
                            shortD = "專注與減壓，特別適合在工作或需要提升專注力時使用",
                            detailed = "Box breathing（方形呼吸），" +
                                    "又稱為四方呼吸，是一種簡單的呼吸技巧，主要用於幫助減輕壓力、提升專注力和促進放鬆。" +
                                    "它可以幫助調節自主神經系統，減少焦慮並改善心理狀態。",
                            inhale = "吸氣 4 秒",
                            exhale = "呼氣 4 秒",
                            hold = "屏氣 4 秒"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                // 控制吸氣、屏氣、吐氣的秒數顯示
                Text(text = "呼吸分鐘", fontSize = 18.sp)

                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // 可左右滾動的 LazyRow
                    LaunchedEffect(centralIndex.value) {
                        // 每次 centralIndex 改變時，滾動到對應位置
                        val targetIndex = centralIndex.value - 1
                        lazyListState.animateScrollToItem(targetIndex - 3.coerceAtLeast(0)) // 保持選中項目在中間
                    }
                    // 可左右滾動的 LazyRow
                    LazyRow(
                        state = lazyListState,
                        flingBehavior = rememberSnapFlingBehavior(lazyListState), // 啟用 Snap 行為
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 1.dp), // 留出空間來實現數字居中
                        horizontalArrangement = Arrangement.spacedBy(15.dp) // 每個數字之間的間距
                    ) {
                        items(items.size) { index ->
                            val item = items[index]
                            val isSelected = centralIndex.value == index + 1  // 判斷當前是否是選中的數字

                            // 顯示數字，選中數字帶有白色圓形背景
                            Box(
                                contentAlignment = Alignment.Center, // 內容居中
                                modifier = Modifier
                                    .size(40.dp) // 當選中時變大
                                    .background(
                                        color = if (isSelected) Color.White else Color.Transparent, // 選中時顯示白色背景
                                        shape = CircleShape // 圓形背景
                                    )
                                    .clickable {
                                        centralIndex.value = index + 1 // 點擊時設置選中項目
                                    }
                            ) {
                                Text(
                                    text = item.toString(),
                                    fontSize = if (isSelected) 24.sp else 20.sp, // 當前選中項目字體放大
                                    color = if (isSelected) Color(0xFF0E0F0F) else Color.Gray, // 選中項目顏色變藍
                                    modifier = Modifier
                                        .padding(8.dp) // 給文字增加內邊距
                                        .fillMaxWidth(), // 填滿可用寬度以實現水平居中
                                    textAlign = TextAlign.Center // 水平居中對齊文字
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                // "開始呼吸"按鈕
                StartButton(onClick = {
                    // 構建 Intent 傳遞選擇的呼吸方法和時間
                    val intent = Intent(context, BreathingActivity::class.java).apply {
                        putExtra("BREATHING_METHOD", selectedBreathingMethod.value)
                        putExtra("TIME_MINUTES", centralIndex.value)
                    }

                    // 啟動 BreathingActivity
                    context.startActivity(intent)
                })
            }
        }
    }
}


@Preview
@Composable
fun BreathScreenPreview() {
    BreathScreenbox(navigateToBreathingActivity = { _, _ -> })
}