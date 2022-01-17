package com.mertceyhan.bitcoinmarket.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mertceyhan.bitcoinmarket.core.ui.theme.ShimmerColorShades

@Composable
fun ShimmerAnimation(
) {

    /*
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
    */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
        Specify animation positions,
        initial Values 0F means it
        starts from 0 position
        */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            // Tween Animates between values over specified [durationMillis]
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    /*
    Create a gradient using the list of colors
    Use Linear Gradient for animating in any direction according to requirement
    start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
    end = Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItems(brush = brush)
}

@Composable
fun ShimmerItems(
    brush: Brush
) {
    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
        val (textCurrency, textPrice, textChangeRate, rowChip, chart,
            textPriceTitle, firstContainer, secondContainer, textAboutChartTitle, textAbout) = createRefs()

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(70.dp)
            .height(20.dp)
            .background(brush = brush)
            .constrainAs(textCurrency) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            })

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(140.dp)
            .height(30.dp)
            .background(brush = brush)
            .constrainAs(textPrice) {
                start.linkTo(parent.start)
                top.linkTo(textCurrency.bottom, margin = 4.dp)
            })

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(40.dp)
            .height(25.dp)
            .background(brush = brush)
            .constrainAs(textChangeRate) {
                top.linkTo(textPrice.top)
                bottom.linkTo(textPrice.bottom)
                end.linkTo(parent.end)
            })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .constrainAs(rowChip) {
                    top.linkTo(textPrice.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, horizontalArrangement = Arrangement.SpaceAround
        ) {
            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .height(25.dp)
                    .background(brush = brush)
            )

            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .height(25.dp)
                    .background(brush = brush)
            )

            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .height(25.dp)
                    .background(brush = brush)
            )

            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .height(25.dp)
                    .background(brush = brush)
            )

            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .height(25.dp)
                    .background(brush = brush)
            )
        }

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(250.dp)
            .background(brush = brush)
            .constrainAs(chart) {
                top.linkTo(rowChip.top, margin = 48.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(60.dp)
            .height(20.dp)
            .background(brush = brush)
            .constrainAs(textPriceTitle) {
                start.linkTo(parent.start)
                top.linkTo(chart.bottom, margin = 34.dp)
            })

        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .width(170.dp)
                .height(90.dp)
                .background(brush = brush)
                .constrainAs(firstContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(textPriceTitle.bottom, margin = 16.dp)
                }
        )

        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .width(170.dp)
                .height(90.dp)
                .background(brush = brush)
                .constrainAs(secondContainer) {
                    start.linkTo(firstContainer.end, margin = 16.dp)
                    top.linkTo(textPriceTitle.bottom, margin = 16.dp)
                }
        )

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(140.dp)
            .height(25.dp)
            .background(brush = brush)
            .constrainAs(textAboutChartTitle) {
                start.linkTo(parent.start)
                top.linkTo(firstContainer.bottom, margin = 16.dp)
            })

        Spacer(modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(250.dp)
            .height(20.dp)
            .background(brush = brush)
            .constrainAs(textAbout) {
                start.linkTo(parent.start)
                top.linkTo(textAboutChartTitle.bottom, margin = 8.dp)
            })
    }
}

