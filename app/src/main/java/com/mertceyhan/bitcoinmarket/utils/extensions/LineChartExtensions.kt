package com.mertceyhan.bitcoinmarket.utils.extensions

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

private const val DEFAULT_ANIMATE_XY_DURATION: Int = 300
private const val MIN_ENTRY_COUNT_FOR_ANIMATION: Int = 30

fun LineChart.setLineDataSet(
    lineDataSet: LineDataSet? = null,
    animateXDuration: Int = 0
) {
    if (lineDataSet != null) {
        clear()
        data = LineData(lineDataSet).apply {
            setDrawValues(false)
        }
    }

    if (lineDataSet?.entryCount.orZero() > MIN_ENTRY_COUNT_FOR_ANIMATION) {
        animateX(if (animateXDuration > 0) animateXDuration else DEFAULT_ANIMATE_XY_DURATION)
    }
}
