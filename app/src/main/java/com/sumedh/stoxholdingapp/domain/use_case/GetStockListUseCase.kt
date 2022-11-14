package com.sumedh.stoxholdingapp.domain.use_case

import com.sumedh.stoxholdingapp.common.Resource
import com.sumedh.stoxholdingapp.domain.model.Stock
import com.sumedh.stoxholdingapp.domain.repository.StockHoldingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetStockListUseCase @Inject constructor(
    private val repository: StockHoldingRepository
){
    suspend operator fun invoke() : Flow<Resource<List<Stock>>> {
        return repository.getStocks()
    }
}