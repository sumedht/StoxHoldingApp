package com.sumedh.stoxholdingapp.data.remote

import com.sumedh.stoxholdingapp.data.dto.Response
import retrofit2.http.GET

interface StockHoldingApi {
    @GET("/v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getStocks() : Response
}