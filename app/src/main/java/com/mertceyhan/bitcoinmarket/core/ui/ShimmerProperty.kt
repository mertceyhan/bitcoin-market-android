package com.mertceyhan.bitcoinmarket.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.shimmer

object ShimmerProperty {
    val color: Color = Color.LightGray
    val shape: Shape = RoundedCornerShape(4.dp)
    val highlight: PlaceholderHighlight = PlaceholderHighlight.shimmer(
        highlightColor = Color.White
    )
}