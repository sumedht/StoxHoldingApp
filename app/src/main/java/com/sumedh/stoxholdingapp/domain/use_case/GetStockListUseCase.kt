package com.sumedh.stoxholdingapp.domain.use_case

import com.sumedh.stoxholdingapp.common.Resource
import com.sumedh.stoxholdingapp.data.dto.toStock
import com.sumedh.stoxholdingapp.domain.model.Stock
import com.sumedh.stoxholdingapp.domain.repository.StockHoldingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetStockListUseCase @Inject constructor(
    private val repository: StockHoldingRepository
){

    operator fun invoke() : Flow<Resource<List<Stock>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getStocks()
            response.error?.let { emit(Resource.Error(response.error.toString())) }
            val stocks = repository.getStocks().data.map { it.toStock() }
            emit(Resource.Success(stocks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connectivity"))
        }
    }
}