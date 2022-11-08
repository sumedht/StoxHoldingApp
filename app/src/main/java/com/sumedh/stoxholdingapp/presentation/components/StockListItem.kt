package com.sumedh.stoxholdingapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.sumedh.stoxholdingapp.domain.model.Stock
import com.sumedh.stoxholdingapp.R

@Composable
fun StockListItem(
    stock : Stock
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stock.symbol,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        append("LTP: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append("${stringResource(R.string.Rs)}${stock.ltp}")
                    }
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stock.quantity.toString(),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                color = if (stock.profitAndLoss < 0 ) Color.Red else Color.Green,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = Color.Black)) {
                        append("P&L: ")
                    }

                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = if (stock.profitAndLoss < 0) Color.Red else Color.Green)) {
                        append("${stringResource(R.string.Rs)}${String.format("%.2f", stock.profitAndLoss)}")
                    }
                }
            )
        }
    }
}