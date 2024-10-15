package com.example.brelax.ui.theme

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brelax.R
import com.google.android.material.color.ColorResourcesOverride
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("nirsmeasurement") { NirsMeasurement(navController) }
                composable("measurement/{minutes}/{seconds}") { backStackEntry ->
                    val minutes = backStackEntry.arguments?.getInt("minutes") ?: 0
                    val seconds = backStackEntry.arguments?.getInt("seconds") ?: 0
                    TimerScreen(minutes, seconds)
                }
            }
        }
    }
}

@Composable
fun NirsMeasurement(navController: NavController) {
    var selectedMinutes by remember { mutableStateOf(1) }
    var selectedSeconds by remember { mutableStateOf(0) }

    // 背景圖片和內容
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.nirsbackground),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(80.dp))
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
            Image(
                painter = painterResource(id = R.drawable.nirscloud),
                contentDescription = "雲朵圖像",
                modifier = Modifier.size(230.dp).padding(vertical = 20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

            TimePicker(
                selectedMinutes = selectedMinutes,
                onMinutesChange = { selectedMinutes = it },
                selectedSeconds = selectedSeconds,
                onSecondsChange = { selectedSeconds = it }
            )
            Spacer(modifier = Modifier.height(80.dp))
            Button1(
                onClick = {
                    navController.navigate("measurement/${selectedMinutes}/${selectedSeconds}")
                },
                text = "開始測量",
            )
        }
    }
}

@Composable
fun TimePicker(
    selectedMinutes: Int,
    onMinutesChange: (Int) -> Unit,
    selectedSeconds: Int,
    onSecondsChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 分鐘選擇
        WheelPicker(
            value = selectedMinutes,
            onValueChange = onMinutesChange,
            range = 0..59
        )
        Text(
            text = " : ",
            style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)
        )
        // 秒數選擇
        WheelPicker(
            value = selectedSeconds,
            onValueChange = onSecondsChange,
            range = 0..59
        )
    }
}

@Composable
fun WheelPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = value)
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.size(100.dp, 150.dp)) {
        // LazyColumn 實現滾動選取器
        LazyColumn(
            state = listState,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(range.toList()) { index, item ->
                val isSelected = index == listState.firstVisibleItemIndex + 1
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 9.8.dp) // 控制每個項目的間距
                ) {
                    Text(
                        text = String.format("%02d", item),
                        style = TextStyle(
                            fontSize = if (isSelected) 30.sp else 24.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.Black else Color.Gray
                        ),
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // 固定的選取區塊，用透明背景表示
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(60.dp))
                .align(Alignment.Center)
                .background(Color.White.copy(alpha = 0.5f))
        )
    }

    // 自動吸附到最近的數字
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val firstVisibleItemIndex = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset

            if (offset > 50) {
                coroutineScope.launch {
                    listState.animateScrollToItem(firstVisibleItemIndex + 1)
                    onValueChange(range.toList()[firstVisibleItemIndex + 1])
                }
            } else {
                coroutineScope.launch {
                    listState.animateScrollToItem(firstVisibleItemIndex)
                    onValueChange(range.toList()[firstVisibleItemIndex])
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewNirsMeasurementScreen() {
    // 在預覽中，忽略 navController
    NirsMeasurement(navController = rememberNavController())
}





    @Composable
    fun TimerScreen(minutes: Int, seconds: Int,) {
        var timeLeft by remember { mutableStateOf((minutes * 60 + seconds).toLong()) }
        var isTimerRunning by remember { mutableStateOf(true) } // 是否正在計時

        // 倒數計時器
        LaunchedEffect(isTimerRunning) {
            if (isTimerRunning) {
                object : CountDownTimer(60000, 1000) { // 60秒倒數
                    override fun onTick(millisUntilFinished: Long) {
                        timeLeft = millisUntilFinished / 1000
                    }

                    override fun onFinish() {
                        isTimerRunning = false
                    }
                }.start()
            }
        }
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
                // NIRS測量中標題
                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = "NIRS測量中",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.tblack),
                )

                Spacer(modifier = Modifier.height(150.dp))
                // 雲的圖示
                Image(
                    painter = painterResource(id = R.drawable.nirscloud), // 這裡要添加自己的雲圖標資源
                    contentDescription = "Cloud Icon",
                    modifier = Modifier.size(230.dp)
                )

                Spacer(modifier = Modifier.height(80.dp))

                // 倒數計時顯示
                Text(
                    text = String.format("%01d:%02d", timeLeft / 60, timeLeft % 60),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.tblack)
                )

                Spacer(modifier = Modifier.height(40.dp))


            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            TimerScreen(minutes = 1, seconds = 30)
        }
    }


