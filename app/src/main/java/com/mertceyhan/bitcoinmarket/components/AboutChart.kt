package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.theme.typography

@Composable
fun AboutChart(
    modifier: Modifier = Modifier,
    aboutChart: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.about_chart),
            style = typography.h5
        )

        Text(
            text = aboutChart,
            style = typography.subtitle2
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutChartPreview() {
    AboutChart(aboutChart = "Average USD market price across major bitcoin exchanges.")
}