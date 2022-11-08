package com.sumedh.stoxholdingapp.presentation
import com.sumedh.stoxholdingapp.domain.model.Stock

data class StockDataState (
     val isLoading: Boolean = false,
     val stocks: List<Stock> = emptyList(),
     val error: String = ""
)