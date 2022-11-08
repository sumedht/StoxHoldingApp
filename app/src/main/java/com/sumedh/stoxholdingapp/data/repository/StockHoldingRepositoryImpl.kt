package com.sumedh.stoxholdingapp.data.repository

import com.sumedh.stoxholdingapp.data.dto.Response
import com.sumedh.stoxholdingapp.data.dto.StockDto
import com.sumedh.stoxholdingapp.data.remote.StockHoldingApi
import com.sumedh.stoxholdingapp.domain.model.HoldingSummary
import com.sumedh.stoxholdingapp.domain.repository.StockHoldingRepository
import javax.inject.Inject

class StockHoldingRepositoryImpl @Inject constructor(
    private val api: StockHoldingApi
) : StockHoldingRepository {
    private var stocks : List<StockDto> = emptyList()

    override suspend fun getStocks(): Response {
        val response = api.getStocks()
        /* Storing value in local cache to satisfy use-case to return value for getHoldingSummary method.
            This can be change to local data storage or remote API
         */
        stocks = response.data
        return response
    }

    override suspend fun getHoldingSummary(): List<StockDto> {
        return stocks;
    }
}