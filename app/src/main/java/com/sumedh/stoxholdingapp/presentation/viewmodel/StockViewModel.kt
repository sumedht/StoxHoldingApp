package com.sumedh.stoxholdingapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumedh.stoxholdingapp.common.Resource
import com.sumedh.stoxholdingapp.domain.use_case.GetHoldingSummaryUseCase
import com.sumedh.stoxholdingapp.domain.use_case.GetStockListUseCase
import com.sumedh.stoxholdingapp.presentation.HoldingSummaryState
import com.sumedh.stoxholdingapp.presentation.StockDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val getStockListUseCase: GetStockListUseCase,
    private val getHoldingSummaryUseCase: GetHoldingSummaryUseCase
) : ViewModel() {

    private val _stockListState = mutableStateOf(StockDataState())
    val stockListState: State<StockDataState> = _stockListState

    private val _holdingSummaryState = mutableStateOf(HoldingSummaryState())
    val holdingSummaryState: State<HoldingSummaryState> = _holdingSummaryState

    init {
        getStocks()
    }

    private fun getStocks() {
        getStockListUseCase().onEach { result ->
            when(result)
            {
                is Resource.Success -> {
                    _stockListState.value = StockDataState(stocks = result.data?: emptyList())
                    getHoldingSummary()
                }

                is Resource.Error -> {
                    _stockListState.value = StockDataState(error = result.message?:"An Unexpected error occured")
                }

                is Resource.Loading -> {
                   _stockListState.value = StockDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHoldingSummary() {
        getHoldingSummaryUseCase().onEach { result ->
            when(result)
            {
                is Resource.Success -> {
                    _holdingSummaryState.value = result.data?.let { HoldingSummaryState(holdingSummary = it) }!!
                }

                is Resource.Error -> {
                    _holdingSummaryState.value = HoldingSummaryState(error = result.message?:"An Unexpected error occured")
                }

                is Resource.Loading -> {
                    _holdingSummaryState.value = HoldingSummaryState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}