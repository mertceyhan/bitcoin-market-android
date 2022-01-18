package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.placeholder
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.ShimmerProperty

enum class TimeRange {
    ONE_DAY, SEVEN_DAYS, THIRTY_DAYS, SIXTY_DAYS, NINETY_DAYS, ONE_YEAR;
}

@Composable
fun TimeRangePicker(
    modifier: Modifier = Modifier,
    selectedTimeRange: TimeRange = TimeRange.THIRTY_DAYS,
    isShowShimmer: Boolean,
    onTimeRangeSelected: (TimeRange) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TimeRangeChip(
            time = stringResource(id = R.string.short_one_day),
            isSelected = selectedTimeRange == TimeRange.ONE_DAY,
            isShowShimmer = isShowShimmer
        ) {
            onTimeRangeSelected(TimeRange.ONE_DAY)
        }

        TimeRangeChip(
            time = stringResource(id = R.string.short_seven_days),
            isSelected = selectedTimeRange == TimeRange.SEVEN_DAYS,
            isShowShimmer = isShowShimmer
        ) {
            onTimeRangeSelected(TimeRange.SEVEN_DAYS)
        }

        TimeRangeChip(
            time = stringResource(id = R.string.short_thirty_days),
            isSelected = selectedTimeRange == TimeRange.THIRTY_DAYS,
            isShowShimmer = isShowShimmer
        ) {
            onTimeRangeSelected(TimeRange.THIRTY_DAYS)
        }

        TimeRangeChip(
            time = stringResource(id = R.string.short_sixty_days),
            isSelected = selectedTimeRange == TimeRange.SIXTY_DAYS,
            isShowShimmer = isShowShimmer
        ) {
            onTimeRangeSelected(TimeRange.SIXTY_DAYS)
        }

        TimeRangeChip(
            time = stringResource(id = R.string.short_ninety_days),
            isSelected = selectedTimeRange == TimeRange.NINETY_DAYS,
            isShowShimmer = isShowShimmer
        ) {
            onTimeRangeSelected(TimeRange.NINETY_DAYS)
        }

        TimeRangeChip(
            time = stringResource(id = R.string.short_one_year),
            isSelected = selectedTimeRange == TimeRange.ONE_YEAR,
            isShowShimmer = isShowShimmer
        ) {
            onTimeRangeSelected(TimeRange.ONE_YEAR)
        }
    }
}

@Composable
private fun TimeRangeChip(
    time: String,
    isSelected: Boolean,
    isShowShimmer: Boolean,
    onTimeRangeSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colors.onBackground else MaterialTheme.colors.background,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onTimeRangeSelected()
            }
            .placeholder(
                visible = isShowShimmer,
                color = ShimmerProperty.color,
                shape = ShimmerProperty.shape,
                highlight = ShimmerProperty.highlight
            ),
    ) {
        Text(
            text = time,
            color = if (isSelected) MaterialTheme.colors.background else MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TimeRangePickerPreview() {
    TimeRangePicker(modifier = Modifier.fillMaxWidth(), isShowShimmer = false)
}