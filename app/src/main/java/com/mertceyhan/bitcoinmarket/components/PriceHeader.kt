package com.mertceyhan.bitcoinmarket.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.placeholder.placeholder
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.ShimmerProperty
import com.mertceyhan.bitcoinmarket.core.ui.theme.typography

@Composable
fun PriceHeader(
    modifier: Modifier = Modifier,
    currency: String,
    price: String,
    changeRate: String,
    isChangeRatePositive: Boolean,
    isShowShimmer: Boolean
) {
    ConstraintLayout(modifier = modifier) {
        val (textCurrency, textPrice, imageChangeRate, textChangeRate) = createRefs()

        Text(
            text = currency,
            style = typography.subtitle2,
            modifier = Modifier.placeholder(
                visible = isShowShimmer,
                color = ShimmerProperty.color,
                shape = ShimmerProperty.shape,
                highlight = ShimmerProperty.highlight
            ).constrainAs(textCurrency) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            })

        Text(text = price, style = typography.h4, modifier = Modifier
            .placeholder(
                visible = isShowShimmer,
                color = ShimmerProperty.color,
                shape = ShimmerProperty.shape,
                highlight = ShimmerProperty.highlight
            )
            .constrainAs(textPrice) {
                start.linkTo(parent.start)
                top.linkTo(textCurrency.bottom)
            }
            .padding(top = 4.dp))

        Image(
            painter = painterResource(id = if (isChangeRatePositive) R.drawable.ic_arrow_positive else R.drawable.ic_arrow_negative),
            contentDescription = null,
            modifier = Modifier
                .placeholder(
                    visible = isShowShimmer,
                    color = ShimmerProperty.color,
                    shape = ShimmerProperty.shape,
                    highlight = ShimmerProperty.highlight
                )
                .constrainAs(imageChangeRate) {
                    top.linkTo(textChangeRate.top)
                    bottom.linkTo(textChangeRate.bottom)
                    end.linkTo(textChangeRate.start)
                    height = Dimension.fillToConstraints
                }
                .padding(4.dp)
        )

        Text(
            text = changeRate,
            style = typography.h6,
            modifier = Modifier
                .placeholder(
                    visible = isShowShimmer,
                    color = ShimmerProperty.color,
                    shape = ShimmerProperty.shape,
                    highlight = ShimmerProperty.highlight
                )
                .constrainAs(textChangeRate) {
                top.linkTo(textPrice.top)
                bottom.linkTo(textPrice.bottom)
                end.linkTo(parent.end)
            })
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PriceHeaderPreview() {
    PriceHeader(
        modifier = Modifier.fillMaxWidth(),
        currency = stringResource(id = R.string.bitcoin_btc),
        price = "$46340.31",
        changeRate = "-21.08%",
        isChangeRatePositive = false,
        isShowShimmer = false
    )
}