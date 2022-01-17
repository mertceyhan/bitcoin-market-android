package com.mertceyhan.bitcoinmarket.features.shimmer

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mertceyhan.bitcoinmarket.components.ShimmerAnimation
import com.mertceyhan.bitcoinmarket.core.ui.theme.BitcoinMarketTheme

@Composable
fun ShimmerScreen() {
    LazyColumn {
        item {
            ShimmerAnimation()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoadingScreenPreview() {
    BitcoinMarketTheme {
        ShimmerScreen()
    }
}