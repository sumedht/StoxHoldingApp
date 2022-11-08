package com.sumedh.stoxholdingapp.domain.model

data class HoldingSummary(
    val currentValue: Double,
    val totalInvestment: Double,
    val totalProfitAndLoss: Double,
    val todayProfitAndLoss: Double
)
