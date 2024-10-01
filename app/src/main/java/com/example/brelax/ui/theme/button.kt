package com.example.brelax.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviousButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    var isPressed by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue =
        if (isPressed) Color.DarkGray.copy(alpha = 0.25f)
        else Color.Transparent,
        animationSpec = tween(100, easing = LinearEasing),
        label = "backgroundColor"
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(50.dp)
            .height(50.dp)
            .clip(shape = CircleShape)
            .background(color)
            .pointerInput(onClick) {
                awaitEachGesture {
                    awaitFirstDown().run { isPressed = true }
                    waitForUpOrCancellation()?.run {
                        isPressed = false
                        onClick()
                    } ?: run { isPressed = false }
                }
            }
    ) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.DarkGray,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

