package com.sumedh.stoxholdingapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sumedh.stoxholdingapp.R
import com.sumedh.stoxholdingapp.presentation.viewmodel.StockViewModel

@Composable
fun StockScreen(
    viewModel: StockViewModel = hiltViewModel()
) {
    val stockState = viewModel.stockListState.value
    val holdingSummaryState = viewModel.holdingSummaryState.value

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                title = {
                    Text(text = "Upstox Holding")
                },
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.0f)
                ) {
                    items(stockState.stocks) { stock ->
                        StockListItem(stock = stock)
                        Divider(color = Color.LightGray,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
                    .background(Color.LightGray)
                )

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
                    .background(Color.White)
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
                                text = "Current Value:",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "${stringResource(R.string.Rs)}${holdingSummaryState.holdingSummary.currentValue}",
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Total Investment:",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "${stringResource(R.string.Rs)}${holdingSummaryState.holdingSummary.totalInvestment}",
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Today's Profit & Loss:",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "${stringResource(R.string.Rs)}${holdingSummaryState.holdingSummary.todayProfitAndLoss}",
                                color = if (holdingSummaryState.holdingSummary.todayProfitAndLoss < 0 ) Color.Red else Color.Green,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Profit & Loss:",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = "${stringResource(R.string.Rs)}${holdingSummaryState.holdingSummary.totalProfitAndLoss}",
                                color = if (holdingSummaryState.holdingSummary.totalProfitAndLoss < 0 ) Color.Red else Color.Green,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                }
            }
            if (stockState.error.isNotBlank() || holdingSummaryState.error.isNotBlank()) {
                Toast.makeText(LocalContext.current, stockState.error, Toast.LENGTH_LONG).show()
            }

            if (stockState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}