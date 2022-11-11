package com.sumedh.stoxholdingapp.domain.use_case

import com.sumedh.stoxholdingapp.common.Resource
import com.sumedh.stoxholdingapp.domain.model.HoldingSummary
import com.sumedh.stoxholdingapp.domain.repository.StockHoldingRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetHoldingSummaryUseCase @Inject constructor(
    private val repository: StockHoldingRepository
) {
    operator fun invoke() : Flow<Resource<HoldingSummary>> = flow {
        try {
            emit(Resource.Loading(true))
            val stocks = repository.getHoldingSummary()
            val currentValue = stocks.fold(0.0) { currentVal, element -> currentVal + element.currentValue }
            val totalInvestment = stocks.fold(0.0) { totalInvestment, element -> totalInvestment + element.investmentValue}
            val totalProfitAndLoss = currentValue.minus(totalInvestment)
            val todayProfitAndLoss = stocks.sumOf { (it.close - it.ltp) * it.quantity }
            emit(Resource.Success(HoldingSummary(currentValue, totalInvestment, totalProfitAndLoss, todayProfitAndLoss)))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}