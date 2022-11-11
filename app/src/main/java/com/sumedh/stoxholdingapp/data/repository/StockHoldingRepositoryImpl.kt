package com.sumedh.stoxholdingapp.data.repository

import com.sumedh.stoxholdingapp.common.Resource
import com.sumedh.stoxholdingapp.data.dto.toStock
import com.sumedh.stoxholdingapp.data.remote.StockHoldingApi
import com.sumedh.stoxholdingapp.domain.model.Stock
import com.sumedh.stoxholdingapp.domain.repository.StockHoldingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StockHoldingRepositoryImpl @Inject constructor(
    private val api: StockHoldingApi
) : StockHoldingRepository {
    private var stocks : List<Stock> = emptyList()

    override suspend fun getStocks(): Flow<Resource<List<Stock>>> {

        return flow {
            try {
                emit(Resource.Loading(true))
                val response = api.getStocks()
                response.error?.let { emit(Resource.Error(response.error.toString())) }
                stocks = response.data.map { it.toStock() }
                emit(Resource.Success(stocks))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage?:"An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connectivity"))
            }
        }
    }

    override suspend fun getHoldingSummary(): List<Stock> {
        return stocks
        }
}