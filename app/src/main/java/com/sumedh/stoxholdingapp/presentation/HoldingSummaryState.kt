package com.sumedh.stoxholdingapp.presentation

import com.sumedh.stoxholdingapp.domain.model.HoldingSummary
import com.sumedh.stoxholdingapp.domain.model.Stock

data class HoldingSummaryState(
    val isLoading: Boolean = false,
    val holdingSummary: HoldingSummary = HoldingSummary(0.0,0.0,0.0,0.0),
    val error: String = ""
)
