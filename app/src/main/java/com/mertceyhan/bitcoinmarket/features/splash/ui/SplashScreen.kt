package com.mertceyhan.bitcoinmarket.features.splash.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.navigation.Screen

@Composable
fun SplashScreen(
    navigationController: NavController? = null,
    alphaAnimationTargetValue: Float = 0f,
    alphaAnimationDurationMillis: Int = 1000
) {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background),
            contentAlignment = Alignment.Center
        ) {
            var targetValue by remember { mutableStateOf(alphaAnimationTargetValue) }

            val alpha: Float by animateFloatAsState(
                targetValue = targetValue,
                animationSpec = tween(alphaAnimationDurationMillis),
                finishedListener = {
                    navigationController?.popBackStack()
                    navigationController?.navigate(Screen.Market.route)
                }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_app),
                contentDescription = null,
                modifier = Modifier.alpha(alpha)
            )

            LaunchedEffect(Unit) {
                targetValue = 1f
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewSplashScreen() {
    SplashScreen(alphaAnimationTargetValue = 1f)
}