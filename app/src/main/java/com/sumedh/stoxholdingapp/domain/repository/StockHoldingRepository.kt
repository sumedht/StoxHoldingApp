package com.sumedh.stoxholdingapp.domain.repository

import com.sumedh.stoxholdingapp.common.Resource
import com.sumedh.stoxholdingapp.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockHoldingRepository {

    suspend fun getStocks() : Flow<Resource<List<Stock>>>

    suspend fun getHoldingSummary() : List<Stock>
}