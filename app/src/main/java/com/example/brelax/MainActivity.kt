package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brelax.ui.theme.BrelaxTheme
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.style.TextAlign
import com.example.brelax.R
import com.example.brelax.ui.theme.BreathScreenUI
import com.example.brelax.ui.theme.Button1
import com.example.brelax.ui.theme.StartButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { MainScreen(navController) }
                composable("breathing") { BreathScreenbox(navController) }
                composable("breathingend"){ BreathScreenUI(navController) }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.happybackground),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 使用 verticalScroll 實現滾動
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // 添加 verticalScroll
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            TopNavBar()
            Spacer(modifier = Modifier.height(150.dp))

            Image(
                painter = painterResource(id = R.drawable.suncloud),
                contentDescription = "Sun and Cloud",
                modifier = Modifier.size(230.dp)
            )
            Spacer(modifier = Modifier.height(150.dp))

            // Go Breathing 按鈕
            StartButton(
                onClick = { navController.navigate("breathing") },
                modifier = Modifier
                    .padding(10.dp)
                    .width(250.dp),


                    )

        }
    }
}

@Preview
@Composable
fun TopNavBar() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .width(280.dp)
            .aspectRatio(4f)
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            painter = painterResource(id = R.drawable.bolticon),
            text = "tES",
            onClick = { /* TODO: Add action */ }
        )
        IconButton(
            painter = painterResource(id = R.drawable.breathicon),
            text = "Breath",
            onClick = { /* TODO: Add action */ }
        )
        IconButton(
            painter = painterResource(id = R.drawable.nirsicon),
            text = "NIRS",
            onClick = { /* TODO: Add action */ }
        )
        IconButton(
            painter = painterResource(id = R.drawable.usericon),
            text = "USER",
            onClick = { /* TODO: Add action */ }
        )
    }
}

@Composable
fun IconButton(
    painter: Painter,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = text,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
                color = Color(0xFF585858)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    BrelaxTheme {
        MainScreen(rememberNavController()) // 提供 NavController
    }
}
