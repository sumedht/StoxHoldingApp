package com.sumedh.stoxholdingapp.domain.model

data class Stock(
    val ltp: Double,
    val quantity: Int,
    val symbol: String,
    val currentValue: Double,
    val investmentValue: Double,
    val profitAndLoss: Double,
    val close: Double
)