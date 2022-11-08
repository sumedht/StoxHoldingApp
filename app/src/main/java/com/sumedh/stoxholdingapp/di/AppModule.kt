package com.sumedh.stoxholdingapp.di

import com.sumedh.stoxholdingapp.common.Constants
import com.sumedh.stoxholdingapp.data.remote.StockHoldingApi
import com.sumedh.stoxholdingapp.data.repository.StockHoldingRepositoryImpl
import com.sumedh.stoxholdingapp.domain.repository.StockHoldingRepository
import com.sumedh.stoxholdingapp.domain.use_case.GetHoldingSummaryUseCase
import com.sumedh.stoxholdingapp.domain.use_case.GetStockListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStockHoldingApi() : StockHoldingApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockHoldingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockHoldingRepository(api: StockHoldingApi) : StockHoldingRepository {
        return StockHoldingRepositoryImpl(api)
    }

}