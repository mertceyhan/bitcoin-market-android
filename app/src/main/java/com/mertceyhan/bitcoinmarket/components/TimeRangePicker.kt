package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimeRangePicker(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TimeRangeChip(time = "1d", isSelected = false) {}

        TimeRangeChip(time = "7d", isSelected = false) {}

        TimeRangeChip(time = "30d", isSelected = true) {}

        TimeRangeChip(time = "60d", isSelected = false) {}

        TimeRangeChip(time = "90d", isSelected = false) {}

        TimeRangeChip(time = "1y", isSelected = false) {}
    }
}

@Composable
private fun TimeRangeChip(
    time: String,
    isSelected: Boolean,
    onSelected: (isSelected: Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(isSelected) }

    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colors.onBackground else MaterialTheme.colors.background,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                selected = !selected
                onSelected(selected)
            },
    ) {
        Text(
            text = time,
            color = if (isSelected) MaterialTheme.colors.background else MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TimeRangePickerPreview() {
    TimeRangePicker(modifier = Modifier.fillMaxWidth())
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TimeRangePickerPreviewDark() {
    TimeRangePicker(modifier = Modifier.fillMaxWidth())
}
