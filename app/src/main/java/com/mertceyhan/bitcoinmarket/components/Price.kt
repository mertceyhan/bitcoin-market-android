package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertceyhan.bitcoinmarket.core.ui.theme.gray700
import com.mertceyhan.bitcoinmarket.core.ui.theme.typography


@Composable
fun Price(
    modifier: Modifier = Modifier,
    openPrice: String,
    closePrice: String,
    highPrice: String,
    lowPrice: String,
    averagePrice: String,
    changePrice: String
) {
    Column(modifier = modifier) {
        Text(text = "Price")

        Row(modifier = Modifier.height(IntrinsicSize.Max)) {
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                Row {
                    Text(
                        text = "Open",
                        style = typography.subtitle2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = openPrice,
                        style = typography.subtitle2,
                        textAlign = TextAlign.Justify
                    )
                }

                Row {
                    Text(
                        text = "High",
                        style = typography.subtitle2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = highPrice,
                        style = typography.subtitle2
                    )
                }

                Row {
                    Text(
                        text = "Average",
                        style = typography.subtitle2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = averagePrice,
                        style = typography.subtitle2
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(0.5.dp),
                color = gray700
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                Row {
                    Text(
                        text = "Close",
                        style = typography.subtitle2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = closePrice,
                        style = typography.subtitle2
                    )
                }

                Row {
                    Text(
                        text = "Low",
                        style = typography.subtitle2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = lowPrice,
                        style = typography.subtitle2
                    )
                }

                Row {
                    Text(
                        text = "Change",
                        style = typography.subtitle2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = changePrice,
                        style = typography.subtitle2
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PricePreview() {
    Price(
        openPrice = "$50,000.00",
        closePrice = "$50,000.00",
        highPrice = "$50,000.00",
        lowPrice = "$50,000.00",
        averagePrice = "$50,000.00",
        changePrice = "$50,000.00",
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PricePreviewDark() {
    Price(
        openPrice = "$50,000.00",
        closePrice = "$50,000.00",
        highPrice = "$50,000.00",
        lowPrice = "$50,000.00",
        averagePrice = "$50,000.00",
        changePrice = "$50,000.00",
    )
}
