package com.sumedh.stoxholdingapp.domain.repository

import com.sumedh.stoxholdingapp.data.dto.Response
import com.sumedh.stoxholdingapp.data.dto.StockDto

interface StockHoldingRepository {

    suspend fun getStocks() : Response

    suspend fun getHoldingSummary() : List<StockDto>
}