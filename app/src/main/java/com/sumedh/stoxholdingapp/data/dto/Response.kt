package com.sumedh.stoxholdingapp.data.dto

data class Response(
    val client_id: String,
    val data: List<StockDto>,
    val error: Any?,
    val response_type: String,
    val timestamp: Long
)