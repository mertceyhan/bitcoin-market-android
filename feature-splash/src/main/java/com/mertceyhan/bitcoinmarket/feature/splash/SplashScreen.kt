package com.mertceyhan.bitcoinmarket.feature.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource

private const val alphaAnimationInitialValue = 0f
private const val alphaAnimationTargetValue = 1f
private const val alphaAnimationDurationMillis = 1000

@Composable
internal fun SplashScreen(
    onSplashScreenComplete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center
    ) {
        var targetValue by remember { mutableStateOf(alphaAnimationInitialValue) }

        val alpha: Float by animateFloatAsState(
            targetValue = targetValue,
            animationSpec = tween(alphaAnimationDurationMillis),
            finishedListener = {
                onSplashScreenComplete()
            }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_app),
            contentDescription = null,
            modifier = Modifier.alpha(alpha)
        )

        LaunchedEffect(Unit) {
            targetValue = alphaAnimationTargetValue
        }
    }
}