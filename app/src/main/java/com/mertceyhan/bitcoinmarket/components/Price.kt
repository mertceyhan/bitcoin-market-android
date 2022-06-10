package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.theme.gray700


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
    ConstraintLayout(modifier = modifier) {
        val (
            viewDivider,
            textPrice,
            textOpen, textOpenPrice,
            textHigh, textHighPrice,
            textAverage, textAveragePrice,
            textClose, textClosePrice,
            textLow, textLowPrice,
            textChange, textChangePrice) = createRefs()


        Text(
            text = stringResource(id = R.string.price),
            style = typography.h5,
            modifier = Modifier.constrainAs(textPrice) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })

        Text(
            text = stringResource(id = R.string.open),
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(textOpen) {
                    top.linkTo(textPrice.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = openPrice,
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp, end = 16.dp)
                .constrainAs(textOpenPrice) {
                    top.linkTo(textOpen.top)
                    bottom.linkTo(textOpen.bottom)
                    end.linkTo(viewDivider.start)
                }
        )

        Text(
            text = stringResource(id = R.string.high),
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(textHigh) {
                    top.linkTo(textOpen.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = highPrice,
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp, end = 16.dp)
                .constrainAs(textHighPrice) {
                    top.linkTo(textHigh.top)
                    bottom.linkTo(textHigh.bottom)
                    end.linkTo(viewDivider.start)
                }
        )

        Text(
            text = stringResource(id = R.string.average),
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(textAverage) {
                    top.linkTo(textHigh.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = averagePrice,
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp, end = 16.dp)
                .constrainAs(textAveragePrice) {
                    top.linkTo(textAverage.top)
                    bottom.linkTo(textAverage.bottom)
                    end.linkTo(viewDivider.start)
                }
        )

        Text(
            text = stringResource(id = R.string.close),
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
                .constrainAs(textClose) {
                    top.linkTo(textPrice.bottom)
                    start.linkTo(viewDivider.end)
                }
        )

        Text(
            text = closePrice,
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(textClosePrice) {
                    top.linkTo(textClose.top)
                    bottom.linkTo(textClose.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.low),
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
                .constrainAs(textLow) {
                    top.linkTo(textClose.bottom)
                    start.linkTo(viewDivider.end)
                }
        )

        Text(
            text = lowPrice,
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(textLowPrice) {
                    top.linkTo(textLow.top)
                    bottom.linkTo(textLow.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.change),
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
                .constrainAs(textChange) {
                    top.linkTo(textLow.bottom)
                    start.linkTo(viewDivider.end)
                }
        )

        Text(
            text = changePrice,
            style = typography.subtitle2,
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(textChangePrice) {
                    top.linkTo(textChange.top)
                    bottom.linkTo(textChange.bottom)
                    end.linkTo(parent.end)
                }
        )

        Divider(modifier = Modifier
            .padding(top = 8.dp)
            .constrainAs(viewDivider) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(textOpen.top)
                bottom.linkTo(textAverage.bottom)
                height = fillToConstraints
            }
            .width(0.5.dp)
            .background(gray700))
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PricePreview() {
    Price(
        openPrice = "$50,000.00",
        closePrice = "$50,000.00",
        highPrice = "$50,000.00",
        lowPrice = "$50,000.00",
        averagePrice = "$50,000.00",
        changePrice = "$50,000.00",
        modifier = Modifier.fillMaxWidth()
    )
}