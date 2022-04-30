package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.theme.BitcoinMarketTheme

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = 0.dp) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading))

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoadingAnimationPreview() {
    BitcoinMarketTheme {
        LoadingAnimation(modifier = Modifier.size(75.dp))
    }
}